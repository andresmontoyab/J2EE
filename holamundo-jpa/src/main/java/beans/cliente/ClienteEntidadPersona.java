package beans.cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import beans.dominio.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ClienteEntidadPersona {

    static Logger log = LogManager.getRootLogger();
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

//Inicia la transaccion
        tx.begin();
//No se debe especificar el ID, ya que se genera en automático
        Persona persona1 = new Persona("Maria", "Gutierrez", "Esparza", "maria@mail.com.mx", "11113333");
        log.debug("Objeto a persistir:" + persona1);
//Persistimos el objeto
        em.persist(persona1);
//Terminamos la transaccion
        tx.commit();
        log.debug("Objeto persistido:" + persona1);
        em.close();
    }
}