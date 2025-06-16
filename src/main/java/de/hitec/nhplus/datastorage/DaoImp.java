package de.hitec.nhplus.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base implementation of the <code>Dao</code> interface providing common database operations.<br>
 *<br>
 * This class provides generic CRUD (Create, Read, Update, Delete) functionality for
 * entities of type <code>T</code>. Concrete DAO classes must implement the required abstract
 * methods to provide entity-specific SQL statements and result parsing.
 *
 * @param <T> Type of the entity.
 */
public abstract class DaoImp<T> implements Dao<T> {
    /**
     * The field for the database connection user for executing SQL statements.
     */
    protected Connection connection;

    /**
     * Constructs a new DaoImp using the given database connection.
     *
     * @param connection the database connection
     */
    protected DaoImp(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts the given object into the database.
     *
     * @param t the object to be created in the database
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void create(T t) throws SQLException {
        getCreateStatement(t).executeUpdate();
    }

    /**
     * Retrieves an object from the database by its id.
     *
     * @param key the ID of the object to retrieve
     * @return the object if found; otherwise <code>null</code>
     * @throws SQLException if a database access error occurs
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        ResultSet result = getReadByIDStatement(key).executeQuery();
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * Retrieves all objects of type <code>T</code> from the database.
     *
     * @return a list containing all objects retrieved from the database
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<T> readAll() throws SQLException {
        return getListFromResultSet(getReadAllStatement().executeQuery());
    }

    /**
     * Updates the database entry of the given object.
     *
     * @param t the object to update
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void update(T t) throws SQLException {
        getUpdateStatement(t).executeUpdate();
    }

    /**
     * Deletes the object with the ID from the database.
     *
     * @param key the ID of the object to delete
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void deleteById(long key) throws SQLException {
        getDeleteStatement(key).executeUpdate();
    }

    /**
     * Constructs a new instance of <code>T</code> from the given <code>ResultSet</code>.
     *
     * @param set the <code>ResultSet</code> containing data for the object
     * @return an instance of <code>T</code> populated with data from the result set
     * @throws SQLException if a database access error occurs
     */
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    /**
     * Creates a list of <code>T</code> objects from the given <code>ResultSet</code>.
     *
     * @param set the <code>ResultSet</code> containing multiple rows of data
     * @return a list of objects constructed from the result set
     * @throws SQLException if a database access error occurs
     */
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    /**
     * Returns a <code>PreparedStatement</code> for inserting the given object.
     *
     * @param t the object to insert
     * @return the <code>PreparedStatement</code> used for insertion
     */
    protected abstract PreparedStatement getCreateStatement(T t);

    /**
     * Returns a <code>PreparedStatement</code> for retrieving an object by ID.
     *
     * @param key the ID of the object to retrieve
     * @return the <code>PreparedStatement</code> used for selection
     */
    protected abstract PreparedStatement getReadByIDStatement(long key);

    /**
     * Returns a <code>PreparedStatement</code> for retrieving all objects.
     *
     * @return the <code>PreparedStatement</code> used for selecting all entries
     */
    protected abstract PreparedStatement getReadAllStatement();

    /**
     * Returns a <code>PreparedStatement</code> for updating the given object.
     *
     * @param t the object to update
     * @return the <code>PreparedStatement</code> used for updating
     */
    protected abstract PreparedStatement getUpdateStatement(T t);

    /**
     * Returns a <code>PreparedStatement</code> for deleting an object by ID.
     *
     * @param key the ID of the object to delete
     * @return the <code>PreparedStatement</code> used for deletion
     */
    protected abstract PreparedStatement getDeleteStatement(long key);
}
