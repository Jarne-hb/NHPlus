package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Abstract base class representing a person with a first name and a surname. <br>
 * <br>
 * Provides basic getter, setter, and JavaFX property methods.
 * This class is intended to be extended by more specific person types.
 */
public abstract class Person {
    /**
     * The person's first name.
     */
    private final SimpleStringProperty firstName;

    /**
     * The person's surname.
     */
    private final SimpleStringProperty surname;

    /**
     * Constructs a new <code>Person</code> instance with the specified first name and surname.
     *
     * @param firstName the first name of the person
     * @param surname   the surname (last name) of the person
     */
    protected Person(String firstName, String surname) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
    }


    /**
     * Returns the person's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Returns the JavaFX property for the first name.
     * Useful for binding in JavaFX applications.
     *
     * @return the first name property
     */
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Returns the person's surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname.get();
    }

    /**
     * Returns the JavaFX property for the surname.
     * Useful for binding in JavaFX applications.
     *
     * @return the surname property
     */
    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    /**
     * Sets the person's surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}
