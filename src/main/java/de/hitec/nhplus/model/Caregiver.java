package de.hitec.nhplus.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Caregiver extends Person{

    private SimpleLongProperty cgID;
    private final SimpleStringProperty telephone;

    public Caregiver(long cgID, String surname, String firstName, String telephone) {
        super(firstName, surname);
        this.cgID = new SimpleLongProperty(cgID);
        this.telephone = new SimpleStringProperty(telephone);
    }

    public Caregiver(String surname, String firstName, String telephone){
        super(firstName, surname);
        this.telephone = new SimpleStringProperty(telephone);
    }

    public long getCgID() {
        return cgID.get();
    }

    public SimpleLongProperty cgIDProperty() {
        return cgID;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone.set(telephone);
    }

    @Override
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.cgID +
                "\nSurname: " + this.getSurname() +
                "\nFirstName: " + this.getFirstName() +
                "\nTelephone: " + this.telephone +
                "\n";
    }
}
