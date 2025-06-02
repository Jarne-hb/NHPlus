package de.hitec.nhplus.model;

public class User {

    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "User" + "\nMNID: " + this.username +
                "\nUsername: " + this.username +
                "\nPassword: " + this.password +
                "\n";
    }
}
