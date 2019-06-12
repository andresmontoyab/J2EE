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
