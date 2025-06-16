package de.hitec.nhplus.presenter;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>UserLoginPresenter</code> contains the entire logic of the User Login view. It verifies user credentials and triggers the transition to the main application window upon successful login..
 */
public class UserLoginPresenter {
    private Main mainApp;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button btnLogin;

    private UserDao dao;

    /**
     * Initializes the presenter by setting up the {@link UserDao} and verifying if user data is accessible. <br>
     * <br>
     * If the database connection fails or users cannot be read, a warning alert is shown to the user.
     */
    public void initialize(){
        this.dao = DaoFactory.getDaoFactory().createUserDao();

        List<User> users = new ArrayList<>();

        try {
            users = dao.readAll();
        } catch (SQLException exception) {
            showLoginAlert("Bitte richte zuerst die Datenbank ein.");
        }
    }

    /**
     * Handles the login process when the login button is clicked. <br>
     * <br>
     * Validates the entered username and password against stored user records.
     * If the login is successful, the main window is shown.
     * If credentials are invalid or fields are empty, an error alert is shown.
     */
    @FXML
    public void handleLogin() {
        String usernameInput = usernameField.getText();
        String passwordInput = passwordField.getText();

        if (usernameInput == null || usernameInput.trim().isEmpty() || 
            passwordInput == null || passwordInput.trim().isEmpty()) {
            showLoginAlert("Benutzername und Passwort müssen ausgefüllt sein!");
            return;
        }

        User user = dao.readByUsername(usernameInput);

        if (user == null) {
            showLoginAlert("Ungültige Anmeldedaten!");
            clearTextFields();
            return;
        }

        if (!passwordInput.equals(user.getPassword())) {
            showLoginAlert("Ungültige Anmeldedaten!");
            clearTextFields();
            return;
        }

        clearTextFields();
        mainApp.mainWindow();
    }

    /**
     * Clears the input fields for username and password.
     */
    private void clearTextFields(){
        usernameField.clear();
        passwordField.clear();
    }

    /**
     * Displays an error alert with the given message to the user.
     *
     * @param message the message to be shown in the alert dialog
     */
    public void showLoginAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Fehler beim Login!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Injects the main application reference so the presenter can switch views after a successful login.
     *
     * @param mainApp the main application instance
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}