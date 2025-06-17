package de.hitec.nhplus.datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;


/**
 * A class for managing the SQLite database connection. <br>
 * <br>
 * This class provides a singleton-style mechanism to retrieve and close a shared
 * {@link Connection} to the application's SQLite database.
 */
public class ConnectionBuilder {

    /**
     * The name of the SQLite database file.
     */
    private static final String DB_NAME = "nursingHome.db";

    /**
     * The JDBC URL for connecting to the SQLite database.
     */
    private static final String URL = "jdbc:sqlite:db/" + DB_NAME;

    /**
     * The shared database connection instance.
     */
    private static Connection connection;

    /**
     * Returns the singleton {@link Connection} to the database. <br>
     * <br>
     * If no connection exists yet, a new one will be created with foreign key
     * constraints enabled.
     *
     * @return the active {@link Connection} to the SQLite database, or {@code null} if connection setup fails
     */
    public static synchronized Connection getConnection() {
        try {
            if (ConnectionBuilder.connection == null) {
                SQLiteConfig configuration = new SQLiteConfig();
                configuration.enforceForeignKeys(true);
                ConnectionBuilder.connection = DriverManager.getConnection(URL, configuration.toProperties());
            }
        } catch (SQLException exception) {
            System.out.println("Verbindung zur Datenbank konnte nicht aufgebaut werden!");
            exception.printStackTrace();
        }
        return ConnectionBuilder.connection;
    }

    /**
     * Closes the current database connection if it is open. <br>
     * <br>
     * After closing, the connection will be set to {@code null}.
     */
    public static synchronized void closeConnection() {
        try {
            if (ConnectionBuilder.connection != null) {
                ConnectionBuilder.connection.close();
                ConnectionBuilder.connection = null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
