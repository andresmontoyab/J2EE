# EJB

EJB stands for Enterprise Java Beans. EJB is an essential part of a J2EE platform

Are Java class with more powerfull features that make these class more powerful and robust:

1. The EJB methods are transactionals.
2. Can be remote.
3. Easy comunication with the DB

When the EJB are executed in the Java EE Container that support EJB, the container add the next services:

1. Security.
2. Async Calls.
3. Web Services
4. Transactions
5. CDI
6. Connection Pool
7. Thread Safety
8. Scheduling
9. Messagin
10. Interceptors.

App Servers Java:

1. GlassFish
2. Jboss
3. Oracle WebLogic

Server Web

1. Tomcat 
2. Jetty

EJB provides an architecture to develop and deploy component based enterprise applications considering robustness,high scalability, and high performance.

EJB is primarily divided into three categories:

1. Session Bean: Session bean stores data of a particular user for a single session. It can be stateful or stateless. It is less resource intensive as compared to entity bean. Session bean gets destroyed as soon as user session terminates.

2. Entity Bean: represent persistent data storage. User data can be saved to database via entity beans and later on can be retrieved from the database in the entity bean.

3. Message Driven Bean: are used in context of JMS (Java Messaging Service). Message Driven Beans can consumes JMS messages from external entities and act accordingly.

@Stateless
@Statefull
@MessageDriven
@Entity
@Singletoon.

## Dependecy Injection

@Inject
@EJB

## Packing EJB

## JPA

Java Persistance API.

Is the standar of java persistence, JPA implements concepts of frameworks ORM  (Object Relational Mapping)

Some implementation are :

1. Hibernate.
2. EclipseLink.
3. OpenJPA.

## JPA Features

One of the main features of the JPA against JDBC is that with JPA we can focus only in Java Code and not SQL Queries as JDBC.

1. Persistance using POJOs

2. No intrusivo.

3. Simple configuration.

4. testing.

# Entity Manager

Es el encargado en realizar los trabajos de persistencia, transaccionalidad , sincronizacion, validacion de mapeo,conversacion de codigo java a sql.

Se obtiene de EntityManagerFactory el cual dependerá de la implementacion de JPA.

PersistenceUnit se encarga de realizar la configuracion del proveedor seleccionado por medio de un objeto persistence.xml

em.persist() -> Guarda en la BD
em.find(x,y) -> Busca informacion
em.remove(x) -> Elimina informacion de la BD

Los metodos de un EJB son transaccionales por default. 


## JPA Ciclo de vida

1. Transitivo 

    persist()

2. Persistivo

    commit() or rollback()

3. Separado 


## Persistir un Objeto JPA

        @Test
        public void testPersistirObjeto() {
            //Paso1 Crea un nuevo Objeto
            //Objeto en estado Transitivo

            Persona persona1 = new Persona(....);

            // Paso 2 Inicia Transanccion
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Paso 3 Ejecuta SQL
            em.persist(persona1);

            // Paso 4 Commit RollBack
            tx.commit();

            // Objeto en estado detached.
        }

## Recuperar objeto de entidad.

        @Test
        public void testEncontrarObjeto() {
            
            // Paso 1 Inicia Transanccion
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Paso 2 Ejecuta SQL de tipo select.
            Persona persona1 = em.find(Persona.class, 23);

            // Paso 3 Commit RollBack
            tx.commit();

            // Objeto en estado detached.
        }

## Actualizar un objeto con JPA

        @Test
        public void actualizarObjeto() {

            // Paso 1 Inicia Transaccion 1
            EntityTransaction tx1 = em.getTransaction();
            tx1.begin();

            // Paso 2 Ejecuta SQL de tipo select 
            Persona persona1 = em.find(Persona.class, 23);

            // Paso 3 Termina Transaccion
            tx.commit();

            // Objeto en estado detached
            log.debug("Objeto recuperado:" + persona1);

            // Paso 4 setValue.
            persona1.setName("Juan");

            // Paso 5 Inicia Transaccion 2
            EntityTransaction tx2 = em.getTransaction();
            tx2.begin();

            // Paso 6 Ejecuta SQL (Es un select, pero al estar modificado, al terminar la transaccion hara un update)
            // como ya tenemos el objeto hacemos solo un merge para resincronizar
            em.merge(persona1);

            // Paso 7 Termina transaccion 2
            // Al momento de hacer commit se revisan las diferencias entre el objeto de la base de datos y el objeto en memoria y se aplican los cambios si los hubiese.
            tx2.commit]();

        }

Transaccion Larga


        // Paso 1 Inicia Transaccion 1
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();

        // Paso 2 Ejecuta SQL de tipo select 
        Persona persona1 = em.find(Persona.class, 23);

        // Paso 3 setValue.
        persona1.setName("Juan");

        // Paso 4 Termina Transaccion
        tx.commit();

## Eliminar un Objeto.

        @Test
        public void actualizarObjeto() {

            // Paso 1 Inicia Transaccion 1
            EntityTransaction tx1 = em.getTransaction();
            tx1.begin();

            // Paso 2 Ejecuta SQL de tipo select 
            Persona persona1 = em.find(Persona.class, 23);

            // Paso 3 Termina Transaccion
            tx.commit();

            // Objeto en estado detached
            log.debug("Objeto recuperado:" + persona1);

            // Paso 4 Inicia Transaccion 2
            EntityTransaction tx2 = em.getTransaction();
            tx2.begin();

            // Paso 5 ejecuta el SQL es un delete.
            persona1.remove(persona1);

            // Paso 6 Termina transaccion 2
            // Al momento de hacer commit se revisan las diferencias entre el objeto de la base de datos y el objeto en memoria y se aplican los cambios si los hubiese.
            tx2.commit]();

        }


## Relaciones en JPA

## OneToOne


        @Entity
        public class Alumno implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_alumno")
                private int idAlumno;

                @OneToOne
                @JoinColumn(name="id_domicilio")    // Relacion Unidireccional one-to-one 
                private Domicilio domicilio;
        }

         @Entity
        public class Domicilio implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_domicilio")
                private int id;

                @OneToOne
                @JoinColumn(name="id_domicilio")
                private int idDomicilio;

                .... more code
        }


## OneToMany 

## ManyToOne 


        @Entity
        public class Instructor implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_instructor")
                private int idInstructor;

                @OneToMany(mappedBy = "instructor)
                @JoinColumn(name="id_domicilio")    // bi-directional many-to-one association to Curso
                private Set<Curso> cursos;
        }

         @Entity
        public class Domicilio implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_curso")
                private int idCurso;

                @ManyToOne
                @JoinColumn(name="id_instructor") // Bi-directional mapping
                private Instructor idInstructor;

                .... more code
        }


## ManyToMany 

        @Entity
        public class Alumno implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_alumno")
                private int idAlumno;

                @OneToOne
                @JoinColumn(name="id_domicilio")    // Relacion Unidireccional one-to-one 
                private Domicilio domicilio;
        }

        
        @Entity
        public class Curso implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_curso")
                private int idCurso;

                @OneToOne
                @JoinColumn(name="id_domicilio")    // Relacion Unidireccional one-to-one 
                private Domicilio domicilio;
        }

Tabla intermedia para normalizar la relaciona muchos a muchos        

        @Entity
        public class Asignacion implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_asignacion")
                private int idAsignacion;

                @ManyToOne
                @JoinColumn(name="id_alumno") // Bi-directional many to one mapping asociado a alumno
                private Alumno Alumno;

                @ManyToOne
                @JoinColumn(name="id_curso") // Bi-directional many to one mapping asociado a Curso
                private Curso idCurso;
                .... more code
        }

## Direccionalidad

### Unidireccional

### Bidireccional 


## Fetching Relacion

### Lazy Loading

Carga Retardada

Este es el tipo por default en las relaciones OneToMany y ManyToMany

        @Entity
        public class Alumno implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_alumno")
                private int idAlumno;

                @OneToOne(fetch=Fetchtype.LAZY)
                @JoinColumn(name="id_domicilio")    // Relacion Unidireccional one-to-one 
                private Domicilio domicilio;
        }


### Eager Loading

Carga Inmediata

Todas las colecciones son recuperadas, por lo tanto se carga toda la informacion.

         @Entity
        public class Alumno implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_alumno")
                private int idAlumno;

                @OneToOne(fetch=Fetchtype.EAGER)
                @JoinColumn(name="id_domicilio")    // Relacion Unidireccional one-to-one 
                private Domicilio domicilio;
        }

## Guardado en Cascada

Se usa para guardar la informacion de la entidad asociada y sus relaciones.

        @Entity
        public class Asignacion implements Serializable {

                @Id
                @GeneratedValue(strategy=GenerationType.IDENTITY)
                @Column(name="id_asignacion")
                private int idAsignacion;

                @ManyToOne(cascade={CascadeType.ALL})
                @JoinColumn(name="id_alumno") // Bi-directional many to one mapping asociado a alumno
                private Alumno Alumno;

        }

Esto implica que cuando se guarde un Objeto Asignacion tambien deberá guardar en BD su respectivo Objeto Alumno.

## JPQL

Java Persistance Query Languague.

1. Lenguaje de consulta similar a SQL pero utiliza objetos Java.

2. Queries Parametrizados

3. Consultas avanzadas con recuperacion de coleccion de datos.

Caracteristicas

1. Uso de Select, from, y where.

2. Sensible a Mayusculas y minisculas.

3. Asociaciones, uso join y fetch

4. Uso de expressiones como +, > between, upper, etc

5. Uso de funciones de agregacion tales como: avg,sum, count, etc

Examples:

        Select p from Persona p;

        Select p from Persona p where p.idPersona = 1;

        Select p from Persona p where p.nombre = 'Juan';

        Select p, p.idPersona from Persona p; // Se obtiene un objeto persona y el id y se guarda en un lista de object.

        Select new mx.com.gm.sga.domain.Persona(p.idPersona) from Persona p;s

Codigo Java.

        @Test
        public void testActualizar() {
            String jpql = null;
            List<Persona> personas;

            EntityTransaction tx1 = em.getTransaction();
            tx1.begin();

            // 

            jpql = "select p from Persona p;
            personas = em.createQuery(jpql).getResultList();

            tx.commit();
        }


## Api Criteria JPA

EL api de criterior es muy utlizado cuando los usuarios pueden adicionar uno o mas filtros a las consultas.

1. Es una alternativa de uso de JPQL o SQL nativo.

2. Permite la combinacion de campos de criterio complejos