package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote {

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Juan", "Perez", "Suarez", "jperez@gmail.com", "55668798"));
        personas.add(new Persona(2, "Martha", "Suarez", "Jimenez", "msuarez@mail.com", "566998811"));
        return personas;
    }

    public Persona encontrarPersonaPorId() {
        return null;
    }

    public Persona encontrarPersonaPorEmail() {
        return null;
    }

    public void registrarPersona(Persona persona) {
    }

    public void modificarPersona(Persona persona) {

    }

    public void eliminarPersona(Persona persona) {

    }
}
