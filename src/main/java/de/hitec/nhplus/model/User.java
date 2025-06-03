package de.hitec.nhplus.model;

public class User {

    private long uid;
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(long uid, String username, String password){
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User" + "\nMNID: " + this.uid +
                "\nUsername: " + this.username +
                "\nPassword: " + this.password +
                "\n";
    }
}
