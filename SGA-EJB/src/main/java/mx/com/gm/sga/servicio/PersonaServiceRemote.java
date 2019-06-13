package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonaServiceRemote {

    List<Persona> listarPersonas();

    Persona encontrarPersonaPorId(Persona persona);

    Persona encontrarPersonaPorEmail();

    void registrarPersona(Persona persona);

    void modificarPersona(Persona persona);

    void eliminarPersona(Persona persona);
}
