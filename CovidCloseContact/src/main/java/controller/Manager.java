package controller;

import javafx.collections.ObservableList;
import model.CloseContact;
import model.Person;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a controller for function that can be called from different classes. This manager deals mainly with functions relating to a database
 */
public class Manager {
    /**
     * Creates an Entity Manager Factory for the Project
     */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("OOP_Final_Proj");

    /**
     * Creates a Person Object with the given parameters and saves it into a MySQL database
     * @param personID Person ID
     * @param firstName Person First Name
     * @param middleName Person Middle Name
     * @param lastName  Person Last Name
     * @param phone Person Phone Number
     * @param email Person Email Address
     */
    public static void addPerson(int personID, String firstName, String middleName, String lastName, String phone, String email) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Person person = new Person(personID, firstName, middleName, lastName, phone, email);
            em.persist(person);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Searches a MySQL database for a person with the given attribute and return that person object if it exists.
     * @param personID Person ID
     * @return Person Object
     */
    public static Person getPerson(int personID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.personID =:personID";
        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        tq.setParameter("personID", personID);
        Person person = null;
        try {
            person = tq.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Person With Given ID Does not Exist");
        } finally {
            em.close();
        }
        return person;
    }

    /**
     * Returns a list of all the Persons in a MySQL database
     * @return A list of Persons Objects
     */
    public static List<Person> getAllPersons() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.personID IS NOT NULL";
        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        List<Person> personList = null;
        try {
            personList = tq.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return personList;
    }


    /**
     * Deletes a Close Contact from a MySQL database that matches the given parameter
     * @param personID Person ID
     */
    public static void deleteCloseContact(int personID){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        String query = "SELECT c FROM CloseContact c WHERE c.person1ID=: personID OR c.person2ID=: personID ";
        TypedQuery<CloseContact> tq = em.createQuery(query, CloseContact.class);
        tq.setParameter("personID",personID);
        try {
            et = em.getTransaction();
            et.begin();
            for (CloseContact x :tq.getResultList()){
                em.remove(x);
            }
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     *Deletes a Person from a MySQL database that matches the given parameter
     * @param personID Person ID
     */
    public static void deletePerson(int personID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Person person = null;
        try {
            deleteCloseContact(personID);
            et = em.getTransaction();
            et.begin();
            person = em.find(Person.class, personID);
            em.remove(person);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Adds a Close Contact object with the given parameters and adds it to a MySQL database
     * @param person1ID Person 1 ID
     * @param person2ID Person 2 ID
     * @param date  Date
     */
    public static void addCloseContact(Person person1ID, Person person2ID, LocalDate date) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            CloseContact contact = new CloseContact(person1ID.getPersonID(),person2ID.getPersonID(),date);
            em.persist(contact);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Returns a list of CloseContact objects that match the given parameters from a MySQL database
     * @param personID Person ID
     * @param date  Date
     * @return  List of CloseContact objects
     */
    public static List<CloseContact> getContacts(int personID,LocalDate date) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM CloseContact c WHERE c.dateContact>=:date AND c.person1ID=:personID OR c.person2ID=:personID";
        TypedQuery<CloseContact> tq = em.createQuery(query, CloseContact.class);
        List<CloseContact> contactList = null;
        tq.setParameter("personID",personID);
        tq.setParameter("date",date);
        try {
            contactList = tq.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return contactList;
    }

    /**
     * Returns a list of all CloseContacts from a MySQL database
     * @return List of CloseContact objects
     */
    public static List<CloseContact> getAllContacts() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM CloseContact c WHERE c.closeContactID IS NOT NULL";
        TypedQuery<CloseContact> tq = em.createQuery(query, CloseContact.class);
        List<CloseContact> contactList = null;
        try {
            contactList = tq.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return contactList;
    }

    /**
     * If person2ID exists in the observable list it returns a string of Person1ID
     * @param data Observable List
     * @param id    ID
     * @return  String
     */
    public static String selectID(ObservableList<CloseContact> data, int id){
        String string ="person2ID";
        for (CloseContact x:data
        ) {
            if (x.getPerson2ID()==id){
                string ="person1ID";

            }

        }
        return string;
    }

    /**
     * Saves all the Data from the MySQL database into two files respective of their table using serializable
     */
    public static void save() {
        try {
            FileOutputStream wdata = new FileOutputStream("Persons.txt");
            ObjectOutputStream wstream = new ObjectOutputStream(wdata);
            wstream.writeObject(getAllPersons());
            System.out.println("Success");
            wstream.flush();
            wstream.close();

            wdata = new FileOutputStream("CloseContacts.txt");
            wstream = new ObjectOutputStream(wdata);
            wstream.writeObject(getAllContacts());
            System.out.println("Success");
            wstream.flush();
            wstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load Data from files into its respective arraylist
     */
    public static void load() {
        ArrayList<Person> personArrayList;
        ArrayList<CloseContact> closeContactArrayList;
        try {
            FileInputStream rdata = new FileInputStream("Persons.txt");
            ObjectInputStream rstream = new ObjectInputStream(rdata);
            personArrayList = (ArrayList<Person>) rstream.readObject();
            rstream.close();
            System.out.println("Persons:"+personArrayList);
            rdata = new FileInputStream("CloseContacts.txt");
            rstream = new ObjectInputStream(rdata);
            closeContactArrayList = (ArrayList<CloseContact>) rstream.readObject();
            rstream.close();
            System.out.println("Close Contacts:"+closeContactArrayList);

        } catch (Exception e) {
            System.out.println("e");
        }
    }
    public static void loop(){
        ArrayList<Person> dummyList = new ArrayList<>();
        while (true){
            Person dummy = new Person(2111313,"12312312313","124124124314","1234124124214","123124124124","3245234234234");
            dummyList.add(dummy);
        }
    }
}
