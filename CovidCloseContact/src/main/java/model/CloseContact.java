package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *  This class allows the application to create a CloseContact object that stores the contact between 2 people and the date it happened.
 */
@Entity
@Table(name = "CloseContact")

public class CloseContact implements Serializable {

    /**
     * Auto Generated ID for close contact
     */
    @Id
    @Column(name = "closeContactID", unique = true)
    private int closeContactID;

    /**
     * Id of person 1
     */
    @Column(name = "person1ID", nullable = false)
    private int person1ID;

    /**
     * Id of person 2
     */
    @Column(name = "person2ID", nullable = false)
    private int person2ID;

    /**
     * The date for which the contact happened
     */
    @Column(name = "dateContact", nullable = false)
    private LocalDate dateContact;

    /**
     * This is a empty constructor with no arguments that is required for hibernate to be able to create a new instance of the class
     *
     */
    public CloseContact() {}

    /**
     * This is the CloseContact constructor that sets initial values for object attributes and initializes the object
     * @param person1ID ID of person 1
     * @param person2ID ID of person 2
     * @param dateContact   The date the contact happened
     */
    public CloseContact( int person1ID, int person2ID, LocalDate dateContact) {
        this.person1ID = person1ID;
        this.person2ID = person2ID;
        this.dateContact = dateContact;
    }

    /**
     *This functions return Close Contact ID from the object
     * @return Returns the Close Contact ID
     */
    public int getCloseContactID() {
        return closeContactID;
    }

    /**
     * This function sets the Close Contact ID of the object
     * @param closeContactID This is the ID of the close contact
     */
    public void setCloseContactID(int closeContactID) {
        this.closeContactID = closeContactID;
    }

    /**
     * This function returns the ID of person one from the object
     * @return Returns a person1ID
     */
    public int getPerson1ID() {
        return person1ID;
    }

    /**
     * This functions sets the person 1 ID of the object
     * @param person1ID This is the Person 1 ID
     */
    public void setPerson1ID(int person1ID) {
        this.person1ID = person1ID;
    }

    /**
     * This function returns the ID of person two from the object
     * @return Returns a person2ID
     */
    public int getPerson2ID() {
        return person2ID;
    }

    /**
     * This functions sets the person 2 ID of the object
     * @param person2ID This is the Person 2 ID
     */
    public void setPerson2ID(int person2ID) {
        this.person2ID = person2ID;
    }

    /**
     * This function returns the of contact between the two persons from the object
     * @return Returns the Date
     */
    public LocalDate getDateContact() {
        return dateContact;
    }

    /**
     * This function sets the date of contact between two persons in the object
     * @param dateContact This is the Date
     */
    public void setDateContact(LocalDate dateContact) {
        this.dateContact = dateContact;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "CloseContact{" +
                "closeContactID=" + closeContactID +
                ", person1ID=" + person1ID +
                ", person2ID=" + person2ID +
                ", dateContact=" + dateContact +
                '}';
    }
}
