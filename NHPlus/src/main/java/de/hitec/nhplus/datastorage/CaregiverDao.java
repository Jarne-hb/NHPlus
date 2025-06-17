package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Caregiver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DaoImp</code>. Overrides methods to generate specific <code>PreparedStatements</code>,
 * to execute the specific SQL Statements.
 */
public class CaregiverDao extends DaoImp<Caregiver> {

    /**
     * The constructor initiates an object of <code>CaregiverDao</code> and passes the connection to its super class.
     *
     * @param connection Object of <code>Connection</code> to execute the SQL-statements.
     */
    public CaregiverDao(Connection connection) {
        super(connection);
    }

    /**
     * Maps a <code>ResultSet</code> of one caregiver to an object of <code>Caregiver</code>.
     *
     * @param resultSet ResultSet with a single row. Columns will be mapped to an object of class <code>Caregiver</code>.
     * @return Object of class <code>Caregiver</code> with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet resultSet) throws SQLException {
        return new Caregiver(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getBoolean(5)
        );
    }

    /**
     * Maps a <code>ResultSet</code> of all caregivers to an <code>ArrayList</code> of <code>Caregiver</code> objects.
     *
     * @param resultSet ResultSet with all rows. The Columns will be mapped to objects of class <code>Caregiver</code>.
     * @return <code>ArrayList</code> with objects of class <code>Caregiver</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Caregiver> caregivers = new ArrayList<>();

        while (resultSet.next()){
            Caregiver caregiver = new Caregiver(resultSet.getLong(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5));
            caregivers.add(caregiver);
        }

        return caregivers;
    }

    /**
     * Generates a <code>PreparedStatement</code> to persist the given object of <code>Caregiver</code>.
     *
     * @param caregiver Object of <code>Caregiver</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given caregiver.
     */
    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "INSERT INTO caregiver (firstname, surname, telNumber, active) " +
                    "VALUES (?,?,?,?)";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getTelephone());
            preparedStatement.setBoolean(4, caregiver.isActive());
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query a caregiver by a given caregiver id (cgID).
     *
     * @param cgID Caregiver id to query.
     * @return <code>PreparedStatement</code> to query the caregiver.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long cgID) {
        PreparedStatement preparedStatement = null;

        try{
            final String sqlStatement = "SELECT * FROM caregiver WHERE cgID = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setLong(1, cgID);
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query all caregivers.
     *
     * @return <code>PreparedStatement</code> to query all caregivers.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "SELECT * FROM caregiver WHERE active = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setBoolean(1, true);
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to update the given caregiver, identified
     * by the id of the caregiver (cgID).
     *
     * @param caregiver Caregiver object to update.
     * @return <code>PreparedStatement</code> to update the given caregiver.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String sqlStatement = "UPDATE caregiver SET " +
                    "firstname = ?, " +
                    "surname = ?, " +
                    "telNumber = ?, " +
                    "active = ? " +
                    "WHERE cgID = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getTelephone());
            preparedStatement.setBoolean(4, caregiver.isActive());
            preparedStatement.setLong(5, caregiver.getCgID());
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to "delete" a caregiver with the given id.
     *
     * @param cgID Id of the caregiver to delete.
     * @return <code>PreparedStatement</code> to delete patient with the given id.
     * <br><br>
     * In this case delete means to set the active state to false.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long cgID) {
        PreparedStatement preparedStatement = null;

        Caregiver caregiver = getCaregiverById(cgID);

        try {
            final String sqlStatement = "UPDATE caregiver SET " +
                    "firstname = ?, " +
                    "surname = ?, " +
                    "telNumber = ?," +
                    "active = ? " +
                    "WHERE cgID = ?";
            preparedStatement = this.connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getTelephone());
            preparedStatement.setBoolean(4, false);
            preparedStatement.setLong(5, caregiver.getCgID());
        } catch (SQLException exception){
            exception.printStackTrace();
        }

        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to get a caregiver with the given id.
     *
     * @param cgID Id of the caregiver to get.
     * @return <code>PreparedStatement</code> to get caregiver with the given id.
     *
     */
    private Caregiver getCaregiverById(long cgID) {
        Caregiver caregiver = null;
        try {
            ResultSet resultSet = getReadByIDStatement(cgID).executeQuery();
            caregiver = getInstanceFromResultSet(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return caregiver;
    }
}