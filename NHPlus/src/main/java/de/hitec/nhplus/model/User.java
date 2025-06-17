package de.hitec.nhplus.model;

/**
 * Represents a user of the system with a unique ID, username, and password. <br>
 * <br>
 * This class is used for authorization purposes.
 */
public class User {
    /**
     * Unique identifier for the user.
     */
    private long uid;

    /**
     * Username used for login or identification.
     */
    private String username;

    /**
     * Password used for authentication.
     */
    private String password;

    /**
     * Constructor to initiate an object of class <code>User</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a user id (uid).
     *
     * @param username Username of the user.
     * @param password Password of the user.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor to initiate an object of class <code>User</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a user id (uid).
     *
     * @param uid User id.
     * @param username Username of the user.
     * @param password Password of the user.
     */
    public User(long uid, String username, String password){
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    /**
     * Returns the user's unique ID.
     *
     * @return the user ID
     */
    public long getUid() {
        return uid;
    }

    /**
     * Sets the user's unique ID.
     *
     * @param uid the new user ID
     */
    public void setUid(long uid) {
        this.uid = uid;
    }

    /**
     * Returns the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the {@code User}.
     *
     * @return a formatted string with user details
     */
    @Override
    public String toString() {
        return "User" + "\nMNID: " + this.uid +
                "\nUsername: " + this.username +
                "\nPassword: " + this.password +
                "\n";
    }
}
