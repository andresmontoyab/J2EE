package mx.com.gm.sga.persistencia;

import mx.com.gm.sga.domain.Persona;

import java.util.List;

public interface PersonaDao {

   List<Persona> findAllPersonas();

   Persona findPersonaById(Persona persona);

   Persona findPersonaByEmail(Persona persona);

   void insertPersona(Persona persona);

   void updatePersona(Persona persona);

   void deletePersona(Persona persona);

}
