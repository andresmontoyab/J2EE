package mx.com.gm.sga.persistencia;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;

import java.util.List;

public interface UsuarioDao {

   List<Usuario> findAllUsuarios();

}
