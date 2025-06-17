package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Patient <br>
 * <br>
 * This class extends {@link Person} and adds patient-specific attributes such as
 * date of birth, care level, room number and a unique identifier (pid).
 */
public class Patient extends Person {
    /**
     * Unique identifier for the patient.
     */
    private SimpleLongProperty pid;

    /**
     * The patient's date of birth as a formatted string (yyyy-MM-dd).
     */
    private final SimpleStringProperty dateOfBirth;

    /**
     * The patient's assigned care level.
     */
    private final SimpleStringProperty careLevel;

    /**
     * The patient's room number.
     */
    private final SimpleStringProperty roomNumber;

    /**
     * List of all treatments assigned to the patient.
     */
    private final List<Treatment> allTreatments = new ArrayList<>();

    /**
     * Constructor to initiate an object of class <code>Patient</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a patient id (pid).
     *
     * @param firstName First name of the patient.
     * @param surname Last name of the patient.
     * @param dateOfBirth Date of birth of the patient.
     * @param careLevel Care level of the patient.
     * @param roomNumber Room number of the patient.
     */
    public Patient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomNumber) {
        super(firstName, surname);
        this.dateOfBirth = new SimpleStringProperty(DateConverter.convertLocalDateToString(dateOfBirth));
        this.careLevel = new SimpleStringProperty(careLevel);
        this.roomNumber = new SimpleStringProperty(roomNumber);
    }

    /**
     * Constructor to initiate an object of class <code>Patient</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a patient id (pid).
     *
     * @param pid Patient id.
     * @param firstName First name of the patient.
     * @param surname Last name of the patient.
     * @param dateOfBirth Date of birth of the patient.
     * @param careLevel Care level of the patient.
     * @param roomNumber Room number of the patient.
     */
    public Patient(long pid, String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomNumber) {
        super(firstName, surname);
        this.pid = new SimpleLongProperty(pid);
        this.dateOfBirth = new SimpleStringProperty(DateConverter.convertLocalDateToString(dateOfBirth));
        this.careLevel = new SimpleStringProperty(careLevel);
        this.roomNumber = new SimpleStringProperty(roomNumber);
    }

    /**
     * Returns the patient ID.
     *
     * @return the unique patient ID
     */
    public long getPid() {
        return pid.get();
    }

    /**
     * Returns the JavaFX property for the patient ID.
     *
     * @return the patient ID property
     */
    public SimpleLongProperty pidProperty() {
        return pid;
    }

    /**
     * Returns the patient's date of birth.
     *
     * @return the date of birth as a string in the format YYYY-MM-DD
     */
    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    /**
     * Returns the JavaFX property for the date of birth.
     *
     * @return the date of birth property
     */
    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    /**
     * Stores the given string as new <code>birthOfDate</code>.
     *
     * @param dateOfBirth as string in the following format: YYYY-MM-DD.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    /**
     * Returns the care level assigned to the patient.
     *
     * @return the care level
     */
    public String getCareLevel() {
        return careLevel.get();
    }

    /**
     * Returns the JavaFX property for the care level.
     *
     * @return the care level property
     */
    public SimpleStringProperty careLevelProperty() {
        return careLevel;
    }

    /**
     * Sets the patient's care level.
     *
     * @param careLevel the new care level
     */
    public void setCareLevel(String careLevel) {
        this.careLevel.set(careLevel);
    }

    /**
     * Returns the patient's room number.
     *
     * @return the room number
     */
    public String getRoomNumber() {
        return roomNumber.get();
    }

    /**
     * Returns the JavaFX property for the room number.
     *
     * @return the room number property
     */
    public SimpleStringProperty roomNumberProperty() {
        return roomNumber;
    }

    /**
     * Sets the patient's room number.
     *
     * @param roomNumber the new room number
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    /**
     * Adds a treatment to the patient's treatment list, if it is not already included.
     *
     * @param treatment the treatment to add
     * @return <code>true</code> if the treatment was added, <code>false</code> if it was already present
     */
    public boolean add(Treatment treatment) {
        if (this.allTreatments.contains(treatment)) {
            return false;
        }
        this.allTreatments.add(treatment);
        return true;
    }

    /**
     * Returns a string representation of the patient and their attributes.
     *
     * @return a formatted string with patient information
     */
    public String toString() {
        return "Patient" + "\nMNID: " + this.pid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nCarelevel: " + this.careLevel +
                "\nRoomnumber: " + this.roomNumber +
                "\n";
    }
}