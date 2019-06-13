package mx.com.gm.sga.cliente;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class ClientePersonaService {

    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente\n");

        try {
            Persona camilo = new Persona("Son", "Rin", "Ga", "am@go", "258693" );
            Integer lastPersonaId = 1;
            // Get Personas
            PersonaServiceRemote personaService = getPersonaService();
            List<Persona> personas = personaService.listarPersonas();

            for (Persona persona : personas) {
                System.out.println(persona);
                lastPersonaId = persona.getIdPersona();
            }

            // Insert Personas
            personaService.registrarPersona(camilo);


            // return onlu one person.
            camilo.setIdPersona(lastPersonaId);
            Persona lastPerson = personaService.encontrarPersonaPorId(camilo);
            System.out.println("Criteria sin condicion \n");
            System.out.println(lastPerson);


            // utilizar criteria api
            List<Persona> personasCriteria = personaService.listarPersonasCriteria();
            for (Persona personaC : personasCriteria) {
                System.out.println(personaC);
            }

            // Criteria con condicion
            camilo.setIdPersona(lastPersonaId - 1);
            Persona criteriaPersona = personaService.encontrarPersonaPorIdCriteria(camilo);
            System.out.println("Criteria con condicion \n");
            System.out.println(criteriaPersona);

            // Criteria con condicion
            camilo.setIdPersona(lastPersonaId);
            Persona criteriaPersonaPredicate = personaService.encontrarPersonaPorIdCriteriaPredicate(camilo);
            System.out.println("Criteria con condicion y Predicates \n");
            System.out.println(criteriaPersonaPredicate);


            System.out.println("\nFin llamada al EJB desde el cliente");
        } catch (NamingException e) {
        }
    }

    private static PersonaServiceRemote getPersonaService() throws NamingException {
        Context jndi = new InitialContext();
        return (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImpl!mx.com.gm.sga.servicio.PersonaServiceRemote");
    }
}
