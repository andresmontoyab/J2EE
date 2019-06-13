package mx.com.gm.sga.cliente;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.domain.Usuario;
import mx.com.gm.sga.servicio.PersonaServiceRemote;
import mx.com.gm.sga.servicio.UsuarioServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class ClienteUsuarioService {

    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente\n");
        try {
            Context jndi = new InitialContext();
            UsuarioServiceRemote usuarioServiceRemote =
                    (UsuarioServiceRemote) jndi.lookup("java:global/sga-jee/UsuarioServiceImpl!mx.com.gm.sga.servicio.UsuarioServiceRemote");
            List<Usuario> personas = usuarioServiceRemote.listarUsuarios();
            for (Usuario usuario : personas) {
                System.out.println(usuario);
            }
            System.out.println("\nFin llamada al EJB desde el cliente");
        } catch (NamingException e) {
        }
    }
}
