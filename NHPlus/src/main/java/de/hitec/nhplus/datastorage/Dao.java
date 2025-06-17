package de.hitec.nhplus.datastorage;

import java.sql.SQLException;
import java.util.List;

/**
 * Generic DAO (Data Access Object) interface defining basic CRUD operations
 * for entities of type <code>T</code>. <br>
 * <br>
 * All methods throw {@link SQLException} to indicate database access issues.
 *
 * @param <T> Type of the entity.
 */
public interface Dao<T> {
    /**
     * Persists the given object in the database.
     *
     * @param t the object to be created
     * @throws SQLException if a database access error occurs
     */
    void create(T t) throws SQLException;

    /**
     * Retrieves an object by its ID.
     *
     * @param key the ID of the object to retrieve
     * @return the object with the given ID, or {@code null} if not found
     * @throws SQLException if a database access error occurs
     */
    T read(long key) throws SQLException;

    /**
     * Retrieves all objects of type <code>T</code> from the database.
     *
     * @return a list of all stored objects
     * @throws SQLException if a database access error occurs
     */
    List<T> readAll() throws SQLException;


    /**
     * Updates the existing database record for the given object.
     *
     * @param t the object to update
     * @throws SQLException if a database access error occurs
     */
    void update(T t) throws SQLException;

    /**
     * Deletes the object with the specified ID from the database.
     *
     * @param key the ID of the object to delete
     * @throws SQLException if a database access error occurs
     */
    void deleteById(long key) throws SQLException;
}
