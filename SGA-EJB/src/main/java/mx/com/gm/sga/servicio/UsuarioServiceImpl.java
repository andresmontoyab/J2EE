package mx.com.gm.sga.servicio;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;
import mx.com.gm.sga.persistencia.PersonaDao;
import mx.com.gm.sga.persistencia.UsuarioDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UsuarioServiceImpl implements UsuarioServiceRemote {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAllUsuarios();
    }
}
