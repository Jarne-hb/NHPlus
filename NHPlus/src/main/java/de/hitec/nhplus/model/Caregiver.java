package de.hitec.nhplus.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a caregiver <br>
 * <br>
 * This class extends {@link Person} and adds caregiver-specific attributes such as
 * telephone number, activity status, and a unique caregiver identifier (cgID).
 */
public class Caregiver extends Person{

    /**
     * The unique ID of the caregiver.
     */
    private SimpleLongProperty cgID;

    /**
     * The caregiver's telephone number.
     */
    private final SimpleStringProperty telephone;

    /**
     * Indicates whether the caregiver is currently active.
     */
    private boolean active;

    /**
     * Constructor to initiate an object of class <code>Caregiver</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a caregiver id (cgID).
     *
     * @param firstName First name of the caregiver.
     * @param surname Last name of the caregiver.
     * @param telephone Telephone number of the caregiver.
     * @param active <code>true</code> if the caregiver is active; <code>false</code> otherwise
     */
    public Caregiver(String surname, String firstName, String telephone, boolean active){
        super(firstName, surname);
        this.telephone = new SimpleStringProperty(telephone);
        this.active = active;
    }

    /**
     * Constructor to initiate an object of class <code>Caregiver</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a caregiver id (cgID).
     *
     * @param cgID Caregiver id.
     * @param firstName First name of the caregiver.
     * @param surname Last name of the caregiver.
     * @param telephone Telephone number of the caregiver.
     * @param active <code>true</code> if the caregiver is active; <code>false</code> otherwise
     */
    public Caregiver(long cgID, String surname, String firstName, String telephone, boolean active) {
        super(firstName, surname);
        this.cgID = new SimpleLongProperty(cgID);
        this.telephone = new SimpleStringProperty(telephone);
        this.active = active;
    }

    /**
     * Returns the caregiver ID.
     *
     * @return the caregiver's unique ID
     */
    public long getCgID() {
        return cgID.get();
    }

    /**
     * Returns the <code>SimpleLongProperty</code> representing the caregiver ID.
     *
     * @return the caregiver ID property
     */
    public SimpleLongProperty cgIDProperty() {
        return cgID;
    }

    /**
     * Returns the caregiver's telephone number.
     *
     * @return the telephone number as a {@code String}
     */
    public String getTelephone() {
        return telephone.get();
    }

    /**
     * Returns the <code>SimpleStringProperty</code> representing the telephone number.
     *
     * @return the telephone number property
     */
    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    /**
     * Sets the caregiver's telephone number.
     *
     * @param telephone the new telephone number
     */
    public void setTelephone(String telephone){
        this.telephone.set(telephone);
    }

    /**
     * Returns whether the caregiver is currently active.
     *
     * @return <code>true</code> if active; <code>false</code> otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the caregiver's active status.
     *
     * @param active <code>true</code> to mark as active; <code>false</code> otherwise
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns a string representation of the caregiver.
     *
     * @return a formatted string containing caregiver details
     */
    @Override
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.cgID +
                "\nSurname: " + this.getSurname() +
                "\nFirstName: " + this.getFirstName() +
                "\nTelephone: " + this.telephone +
                "\nActive: " + this.active +
                "\n";
    }
}
