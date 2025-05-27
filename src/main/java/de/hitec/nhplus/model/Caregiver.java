package de.hitec.nhplus.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Caregiver extends Person{

    private SimpleLongProperty cgId;
    private final SimpleStringProperty telNumber;

    public Caregiver(long cgId, String firstName, String surname, String telNumber) {
        super(firstName, surname);
        this.cgId = new SimpleLongProperty(cgId);
        this.telNumber = new SimpleStringProperty(telNumber);
    }

    public Caregiver(String firstName, String surname, String telNumber){
        super(firstName, surname);
        this.telNumber = new SimpleStringProperty(telNumber);
    }

    public long getCgId() {
        return cgId.get();
    }

    public SimpleLongProperty cgIdProperty() {
        return cgId;
    }

    public String getTelNumber() {
        return telNumber.get();
    }

    public SimpleStringProperty telNumberProperty() {
        return telNumber;
    }

    public void setTelNumber(String telNumber){
        this.telNumber.set(telNumber);
    }

    @Override
    public String toString() {
        return "Caregiver{" +
                "cgId=" + cgId +
                "firstName=" + this.getFirstName() +
                "surname=" + this.getSurname() +
                ", telNumber=" + telNumber +
                '}';
    }
}
