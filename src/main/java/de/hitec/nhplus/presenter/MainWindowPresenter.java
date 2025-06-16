package de.hitec.nhplus.presenter;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.ConnectionBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The <code>MainWindowPresenter</code> class controls the main window layout and navigation.<br>
 * <br>
 * It handles user actions from the main menu and loads the first view (patients, treatments, caregivers)
 * into the center of the main {@link BorderPane}.
 */
public class MainWindowPresenter {

    @FXML
    private BorderPane mainBorderPane;

    /**
     * Loads and displays the view showing all patients.
     *
     * @param event the triggering {@link ActionEvent}
     */
    @FXML
    private void handleShowAllPatient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads and displays the view showing all treatments.
     *
     * @param event the triggering {@link ActionEvent}
     */
    @FXML
    private void handleShowAllTreatments(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads and displays the view showing all caregivers.
     *
     * @param actionEvent the triggering {@link ActionEvent}
     */
    public void handleShowAllCaregivers(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    }

