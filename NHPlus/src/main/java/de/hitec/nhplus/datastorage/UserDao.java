package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DaoImp</code>. Overrides methods to generate specific <code>PreparedStatements</code>,
 * to execute the specific SQL Statements.
 */
public class UserDao extends DaoImp<User> {
    /**
     * The constructor initiates an object of <code>UserDao</code> and passes the connection to its super class.
     *
     * @param connection Object of <code>Connection</code> to execute the SQL-statements.
     */
    public UserDao(Connection connection) {
        super(connection);
    }

    /**
     * Maps a <code>ResultSet</code> of one user to an object of <code>User</code>.
     *
     * @param resultSet ResultSet with a single row. Columns will be mapped to an object of class <code>User</code>.
     * @return Object of class <code>User</code> with the data from the resultSet.
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3));
    }

    /**
     * Maps a <code>ResultSet</code> of all users to an <code>ArrayList</code> of <code>User</code> objects.
     *
     * @param resultSet ResultSet with all rows. The Columns will be mapped to objects of class <code>User</code>.
     * @return <code>ArrayList</code> with objects of class <code>User</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            userList.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return userList;
    }

    /**
     * Generates a <code>PreparedStatement</code> to persist the given object of <code>User</code>.
     *
     * @param user Object of <code>User</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given user.
     */
    @Override
    protected PreparedStatement getCreateStatement(User user) {
        PreparedStatement preparedStatement = null;

        try {
            final String sql = "INSERT INTO users (username, password)" + "VALUES (?,?)";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query a user by a given user id (uid).
     *
     * @param uid User id to query.
     * @return <code>PreparedStatement</code> to query the user.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long uid) {
        PreparedStatement preparedStatement = null;

        try {
            final String sql = "SELECT * FROM users WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, uid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query all users.
     *
     * @return <code>PreparedStatement</code> to query all users.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement preparedStatement = null;

        try {
            final String sql = "SELECT * FROM users";
            preparedStatement = this.connection.prepareStatement(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to update the given user, identified
     * by the id of the user (uid).
     *
     * @param user User object to update.
     * @return <code>PreparedStatement</code> to update the given user.
     */
    @Override
    protected PreparedStatement getUpdateStatement(User user) {
        PreparedStatement preparedStatement = null;

        try {
            final String sql = "UPDATE users SET uid = ?, username = ?, password = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getUid());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to delete a user with the given id.
     *
     * @param uid Id of the user to delete.
     * @return <code>PreparedStatement</code> to delete user with the given id.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long uid) {
        PreparedStatement preparedStatement = null;

        try {
            final String sql = "DELETE FROM users WHERE uid = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, uid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Retrieves a <code>User</code> object from the database that matches the given username.
     *
     * @param username the username of the user to retrieve
     * @return a <code>User</code>  object containing the user's data if found; <code>null</code> if no user is found or an error occurs.
     */
    public User readByUsername(String username){
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            final String sql = "SELECT * FROM users WHERE username = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User(resultSet.getLong("uid"), resultSet.getString("username"), resultSet.getString("password"));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

       return user;
    }
}
