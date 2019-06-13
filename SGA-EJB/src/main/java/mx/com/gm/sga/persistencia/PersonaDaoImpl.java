package mx.com.gm.sga.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import mx.com.gm.sga.domain.Persona;

@Stateless
public class PersonaDaoImpl implements PersonaDao {

    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;

    @Override
    public List<Persona> findAllPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public List<Persona> findAllPersonasCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = cb.createQuery(Persona.class);
        Root<Persona> fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
        TypedQuery<Persona> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Persona findPersonaById(Persona persona) {
        Query query = em.createQuery("select p from Persona p where p.idPersona = :id");
        query.setParameter("id", persona.getIdPersona());
        return (Persona) query.getSingleResult();
    }

    @Override
    public Persona findPersonaByIdCriteria(Persona persona) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = cb.createQuery(Persona.class);
        Root<Persona> fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"), persona.getIdPersona()));
        TypedQuery<Persona> query = em.createQuery(criteriaQuery);
        return (Persona) query.getSingleResult();
    }

    @Override
    public Persona findPersonaByIdCriteriaPredicate(Persona persona) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> criteriaQuery = cb.createQuery(Persona.class);
        Root<Persona> fromPersona = criteriaQuery.from(Persona.class);
        List<Predicate> criterios = new ArrayList<>();
         if(persona.getIdPersona() != null) {
             ParameterExpression<Integer> p = cb.parameter(Integer.class, "idPersona");
             criterios.add(cb.equal(fromPersona.get("idPersona"), p));
         }

         if(criterios.isEmpty()) {
             throw new RuntimeException("No hay criterios");
         } else if (criterios.size() == 1){
             criteriaQuery.where(criterios.get(0));
         } else {
             criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
         }

        TypedQuery<Persona> query = em.createQuery(criteriaQuery);

         if (persona.getIdPersona() != null ){
             query.setParameter("idPersona", persona.getIdPersona());
         }

         return query.getSingleResult();
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        Query query = em.createQuery("from Persona p where p.email =: email");
        query.setParameter("email", persona.getEmail());
        return (Persona) query.getSingleResult();
    }

    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        em.merge(persona);
        em.remove(persona);
    }
}