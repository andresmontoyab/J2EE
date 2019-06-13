package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UsuarioServiceRemote {

    List<Usuario> listarUsuarios();

}
