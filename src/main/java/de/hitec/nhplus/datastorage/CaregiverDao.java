package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Caregiver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDao extends DaoImp<Caregiver> {

    public CaregiverDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        return new Caregiver(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
    }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Caregiver> caregivers = new ArrayList<>();

        while (resultSet.next()){
            Caregiver caregiver = new Caregiver(resultSet.getLong(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4));
            caregivers.add(caregiver);
        }

        return caregivers;
    }

    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "INSERT INTO caregiver (firstname, surname, telNumber) " +
                    "VALUES (?,?,?)";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getTelephone());
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement getReadByIDStatement(long key) {
        PreparedStatement preparedStatement = null;

        try{
            final String sqlStatement = "SELECT * FROM caregiver WHERE cgId = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setLong(1, key);
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "SELECT * FROM caregiver";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "UPDATE caregiver SET " +
                    "firstname = ?, " +
                    "surname = ?, " +
                    "telNumber = ? " + // Komma hier entfernt
                    "WHERE cgId = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getTelephone());
            preparedStatement.setLong(4, caregiver.getCgID());
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(long key) {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "DELETE FROM caregiver WHERE cgId = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setLong(1, key);
        } catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }
}