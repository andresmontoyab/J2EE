package mx.com.gm.sga.cliente;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class ClientePersonaServiceConIP {

    public static void main(String[] args) {
        System.out.println("Iniciando Llamada al EJB desde el cliente");

        try {
            Context jndi = new InitialContext(definirPropiedades());
            PersonaServiceRemote personaService =
                    (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImpl!mx.com.gm.sga.servicio.PersonaServiceRemote");
            List<Persona> personas = personaService.listarPersonas();

            for (Persona persona : personas) {
                System.out.println(persona);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static Properties definirPropiedades() {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

        return props;
    }
}
