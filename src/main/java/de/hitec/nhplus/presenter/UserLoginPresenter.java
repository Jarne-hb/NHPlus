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

public class UserLoginPresenter {
    private Main mainApp;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button btnLogin;

    private UserDao dao;

    public void initialize(){
        this.dao = DaoFactory.getDaoFactory().createUserDao();

        List<User> users = new ArrayList<>();

        try {
            users = dao.readAll();
        } catch (SQLException exception) {
            showLoginAlert("Bitte richte zuerst die Datenbank ein.");
        }
    }

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

    private void clearTextFields(){
        usernameField.clear();
        passwordField.clear();
    }

    public void showLoginAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Fehler beim Login!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}