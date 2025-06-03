package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends DaoImp<User> {
    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected User getInstanceFromResultSet(ResultSet set) throws SQLException {
        return new User(set.getLong(1), set.getString(2), set.getString(3));
    }

    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        while (set.next()) {
            userList.add(new User(set.getLong(1), set.getString(2), set.getString(3)));
        }
        return userList;
    }

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
