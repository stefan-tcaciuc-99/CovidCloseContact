package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class CloseContactTest {
    private CloseContact closeContact;

    @BeforeEach
    void setUp() {
        closeContact = new CloseContact(1234, 12314, LocalDate.of(2020, Month.JANUARY, 18));
    }

    @Test
    public void testSetDateContactFail() {
        try {

            closeContact.setDateContact(LocalDate.of(-12, -12, -12));
            fail("Negative dates should trigger an exception");
        } catch (Exception e) {
            System.out.println("Illegal Argument:" + e.getMessage());
        }
    }

    @Test
    public void testSetDateContact() {
        closeContact.setDateContact(LocalDate.of(1999, Month.JANUARY, 12));
        LocalDate expectedDate = LocalDate.of(1999, Month.JANUARY, 12);
        assertEquals(expectedDate, closeContact.getDateContact());
    }

    @Test
    public void testSetPerson1ID() {
        closeContact.setPerson1ID(123);
        assertEquals(123, closeContact.getPerson1ID());
    }

    @Test
    public void testSetPerson2ID() {
        closeContact.setPerson1ID(662);
        assertEquals(662, closeContact.getPerson1ID());
    }

}