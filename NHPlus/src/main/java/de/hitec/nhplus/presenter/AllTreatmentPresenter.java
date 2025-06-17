package de.hitec.nhplus.presenter;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.CaregiverDao;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Caregiver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The <code>AllTreatmentPresenter</code> contains the entire logic of the treatment view. It determines which data is displayed and how to react to events.
 */
public class AllTreatmentPresenter {

    @FXML
    private TableView<Treatment> tableView;

    @FXML
    private TableColumn<Treatment, Integer> columnId;

    @FXML
    private TableColumn<Treatment, Integer> columnPid;

    @FXML
    private TableColumn<Treatment, String> columnDate;

    @FXML
    private TableColumn<Treatment, String> columnBegin;

    @FXML
    private TableColumn<Treatment, String> columnEnd;

    @FXML
    private TableColumn<Treatment, String> columnDescription;

    @FXML
    private ComboBox<String> comboBoxPatientSelection;

    @FXML
    public ComboBox<String> comboBoxCaregiverSelection;

    @FXML
    private Button buttonDelete;

    private final ObservableList<Treatment> treatments = FXCollections.observableArrayList();
    private TreatmentDao dao;
    private final ObservableList<String> patientSelection = FXCollections.observableArrayList();
    private final ObservableList<String> caregiverSelection = FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;
    private ArrayList<Caregiver> caregiverList;

    /**
     * When <code>initialize()</code> gets called, all fields are already initialized. At this point of the lifecycle of the Presenter, the fields can be accessed and
     * configured.
     */
    public void initialize() {
        readAllAndShowInTableView();
        comboBoxPatientSelection.setItems(patientSelection);
        comboBoxPatientSelection.getSelectionModel().select(0);

        comboBoxCaregiverSelection.setItems(caregiverSelection);
        comboBoxCaregiverSelection.getSelectionModel().select(0);

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("tid"));
        this.columnPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.columnBegin.setCellValueFactory(new PropertyValueFactory<>("begin"));
        this.columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tableView.setItems(this.treatments);

        // Disabling the button to delete treatments as long, as no treatment was selected.
        this.buttonDelete.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldTreatment, newTreatment) ->
                        AllTreatmentPresenter.this.buttonDelete.setDisable(newTreatment == null));

        this.createComboBoxData();
    }

    /**
     * Loads all treatments from the database and displays them in the table view.
     */
    public void readAllAndShowInTableView() {
        this.treatments.clear();
        comboBoxPatientSelection.getSelectionModel().select(0);
        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            this.treatments.addAll(dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Populates the patient and caregiver combo boxes with all entries from the database.
     * Adds an "alle" (all) option to each ComboBox for global filtering.
     */
    private void createComboBoxData() {
        PatientDao pDao = DaoFactory.getDaoFactory().createPatientDAO();
        CaregiverDao cgDao = DaoFactory.getDaoFactory().createCaregiverDao();
        try {
            patientList = (ArrayList<Patient>) pDao.readAll();
            this.patientSelection.add("alle");
            for (Patient patient : patientList) {
                this.patientSelection.add(patient.getSurname());
            }

            caregiverList = (ArrayList<Caregiver>) cgDao.readAll();
            this.caregiverSelection.add("alle");
            for (Caregiver caregiver : caregiverList) {
                this.caregiverSelection.add(caregiver.getSurname());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles filtering treatments based on the selected patient and caregiver from the combo boxes.
     * Loads filtered data from the database and updates the TableView.
     */
    @FXML
    private void handleComboBox() {
        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();
        this.treatments.clear();

        String selectedPatient = comboBoxPatientSelection.getSelectionModel().getSelectedItem();
        String selectedCaregiver = comboBoxCaregiverSelection.getSelectionModel().getSelectedItem();

        try {
            if ("alle".equals(selectedPatient) && "alle".equals(selectedCaregiver)) {
                this.treatments.addAll(this.dao.readAll());
            } else if (!"alle".equals(selectedPatient) && "alle".equals(selectedCaregiver)) {
                Patient patient = searchInPatientList(selectedPatient);
                if (patient != null) {
                    this.treatments.addAll(this.dao.readTreatmentsByPid(patient.getPid()));
                }
            } else if ("alle".equals(selectedPatient)) {
                Caregiver caregiver = searchInCaregiverList(selectedCaregiver);
                if (caregiver != null) {
                    this.treatments.addAll(this.dao.readTreatmentsByCgID(caregiver.getCgID()));
                }
            } else {
               Patient patient = searchInPatientList(selectedPatient);
               Caregiver caregiver = searchInCaregiverList(selectedCaregiver);
               if (patient != null && caregiver != null) {
                   this.treatments.addAll(this.dao.readTreatmentsByPidAndCgID(patient.getPid(),caregiver.getCgID()));
               }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Searches for a patient in the loaded patient list by surname.
     *
     * @param surname the surname to search for
     * @return the matching Patient object, or null if not found
     */
    private Patient searchInPatientList(String surname) {
        for (Patient patient : this.patientList) {
            if (patient.getSurname().equals(surname)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Searches for a caregiver in the loaded caregiver list by surname.
     *
     * @param surname the surname to search for
     * @return the matching Caregiver object, or null if not found
     */
    private Caregiver searchInCaregiverList(String surname) {
        for (Caregiver caregiver : this.caregiverList) {
            if (caregiver.getSurname().equals(surname)) {
                return caregiver;
            }
        }
        return null;
    }

    /**
     * Deletes the selected treatment from both the TableView and the database.
     * Triggered by the delete button.
     */
    @FXML
    public void handleDelete() {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.treatments.remove(index);
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.deleteById(t.getTid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the creation of a new treatment. Opens a new window for treatment input.
     * Requires a patient and caregiver to be selected from the combo boxes.
     * Displays an alert if no patient is selected.
     */
    @FXML
    public void handleNewTreatment() {
        try {
            String selectedPatient = this.comboBoxPatientSelection.getSelectionModel().getSelectedItem();
            String selectedCaregiver = this.comboBoxCaregiverSelection.getSelectionModel().getSelectedItem();
            Patient patient = searchInPatientList(selectedPatient);
            Caregiver caregiver = searchInCaregiverList(selectedCaregiver);
            newTreatmentWindow(patient, caregiver);
        } catch (NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Patient für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Patienten aus!");
            alert.showAndWait();
        }
    }

    /**
     * Registers a double-click event on the TableView rows.
     * Opens a detailed treatment view if a treatment is double-clicked.
     */
    @FXML
    public void handleMouseClick() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItem() != null)) {
                int index = this.tableView.getSelectionModel().getSelectedIndex();
                Treatment treatment = this.treatments.get(index);
                treatmentWindow(treatment);
            }
        });
    }

    /**
     * Opens the New Treatment creation window.
     *
     * @param patient   the selected patient, may be null
     * @param caregiver the selected caregiver, may be null
     */
    public void newTreatmentWindow(Patient patient, Caregiver caregiver) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();

            NewTreatmentPresenter controller = loader.getController();
            controller.initialize(this, stage, patient, caregiver);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens the Treatment detail window for an existing treatment.
     *
     * @param treatment the treatment to view or edit
     */
    public void treatmentWindow(Treatment treatment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();
            TreatmentPresenter controller = loader.getController();
            controller.initialize(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
