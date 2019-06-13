package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.persistencia.PersonaDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote {

    @Inject
    private PersonaDao personaDao;

    public List<Persona> listarPersonas() {
        return personaDao.findAllPersonas();
    }

    @Override
    public List<Persona> listarPersonasCriteria() {
        return personaDao.findAllPersonasCriteria();
    }

    public Persona encontrarPersonaPorId(Persona persona) {
        return personaDao.findPersonaById(persona);
    }

    @Override
    public Persona encontrarPersonaPorIdCriteria(Persona persona) {
        return personaDao.findPersonaByIdCriteria(persona);
    }

    @Override
    public Persona encontrarPersonaPorIdCriteriaPredicate(Persona persona) {
        return personaDao.findPersonaByIdCriteriaPredicate(persona);
    }

    public Persona encontrarPersonaPorEmail() {
        return null;
    }

    public void registrarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    public void modificarPersona(Persona persona) {
    }

    public void eliminarPersona(Persona persona) {
    }
}
