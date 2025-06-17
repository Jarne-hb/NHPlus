package de.hitec.nhplus.datastorage;

/**
 * Factory class for creating DAO (Data Access Object) instances.<br>
 * <br>
 * This class implements the Singleton pattern to provide a single shared instance
 * for creating DAO objects with managed database connections.
 */
public class DaoFactory {

    /**
     * The single instance of the <code>DaoFactory</code>.
     */
    private static DaoFactory instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Use {@link #getDaoFactory()} to obtain the instance.
     */
    private DaoFactory() {
    }

    /**
     * Returns the singleton instance of the <code>DaoFactory</code>.
     *
     * @return the single <code>DaoFactory</code> instance
     */
    public static DaoFactory getDaoFactory() {
        if (DaoFactory.instance == null) {
            DaoFactory.instance = new DaoFactory();
        }
        return DaoFactory.instance;
    }

    /**
     * Creates a new instance of <code>TreatmentDao</code> using a fresh database connection.
     *
     * @return a new <code>TreatmentDao</code> instance
     */
    public TreatmentDao createTreatmentDao() {
        return new TreatmentDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new instance of <code>PatientDao</code> using a fresh database connection.
     *
     * @return a new <code>PatientDao</code> instance
     */
    public PatientDao createPatientDAO() {
        return new PatientDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new instance of <code>CaregiverDao</code> using a fresh database connection.
     *
     * @return a new <code>CaregiverDao</code> instance
     */
    public CaregiverDao createCaregiverDao() {
        return new CaregiverDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new instance of <code>UserDao</code> using a fresh database connection.
     *
     * @return a new <code>UserDao</code> instance
     */
    public UserDao createUserDao() {
        return new UserDao(ConnectionBuilder.getConnection());
    }
}
