package controller;

import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Person expectedPerson;
    @BeforeEach
    void setUp() {
         expectedPerson = new Person(2,"2","2","2","2","2");

    }
    @Test
    public void getPersonTest(){
        assertEquals(expectedPerson.getPersonID(),Manager.getPerson(2).getPersonID());

    }
    @Test
    public void getPersonTestFail(){
        try {
            assertEquals(expectedPerson.getPersonID(),Manager.getPerson(-12).getPersonID());
            fail("There cannot be a person with negative id in the db");

        }catch (Exception e){
            System.out.println(" ");
        }

    }
}