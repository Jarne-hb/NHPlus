package de.hitec.nhplus.presenter;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Caregiver;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The <code>NewTreatmentPresenter</code> contains the entire logic of the new treatment view. It determines which data is displayed and how to react to events.
 */
public class NewTreatmentPresenter {

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labelCaregiverName;

    @FXML
    private Label labelCaregiverTelephone;

    @FXML
    private TextField textFieldBegin;

    @FXML
    private TextField textFieldEnd;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextArea textAreaRemarks;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button buttonAdd;

    private AllTreatmentPresenter controller;
    private Patient patient;
    private Stage stage;
    private Caregiver caregiver;

    /**
     * When <code>initialize()</code> gets called, all fields are already initialized. At this point of the lifecycle of the Presenter, the fields can be accessed and
     * configured.
     */
    public void initialize(AllTreatmentPresenter controller, Stage stage, Patient patient, Caregiver caregiver) {
        this.controller= controller;
        this.patient = patient;
        this.caregiver = caregiver;
        this.stage = stage;

        this.buttonAdd.setDisable(true);
        ChangeListener<String> inputNewPatientListener = (observableValue, oldText, newText) ->
                NewTreatmentPresenter.this.buttonAdd.setDisable(NewTreatmentPresenter.this.areInputDataInvalid());
        this.textFieldBegin.textProperty().addListener(inputNewPatientListener);
        this.textFieldEnd.textProperty().addListener(inputNewPatientListener);
        this.textFieldDescription.textProperty().addListener(inputNewPatientListener);
        this.textAreaRemarks.textProperty().addListener(inputNewPatientListener);
        this.datePicker.valueProperty().addListener((observableValue, localDate, t1) -> NewTreatmentPresenter.this.buttonAdd.setDisable(NewTreatmentPresenter.this.areInputDataInvalid()));
        this.datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : DateConverter.convertLocalDateToString(localDate);
            }

            @Override
            public LocalDate fromString(String localDate) {
                return DateConverter.convertStringToLocalDate(localDate);
            }
        });
        this.showPatientData();
    }

    /**
     * Displays basic patient and caregiver information in the form labels.
     */
    private void showPatientData(){
        this.labelFirstName.setText(patient.getFirstName());
        this.labelSurname.setText(patient.getSurname());
        this.labelCaregiverName.setText(caregiver.getSurname() + ", " + caregiver.getFirstName());
        this.labelCaregiverTelephone.setText(caregiver.getTelephone());
    }

    /**
     * Handles the "Add" button event. Validates the input, creates a new {@link Treatment},
     * stores it in the database, updates the table view, and closes the window.
     */
    @FXML
    public void handleAdd(){
        LocalDate date = this.datePicker.getValue();
        LocalTime begin = DateConverter.convertStringToLocalTime(textFieldBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(textFieldEnd.getText());
        String description = textFieldDescription.getText();
        String remarks = textAreaRemarks.getText();
        Treatment treatment = new Treatment(patient.getPid(), caregiver.getCgID(), date, begin, end, description, remarks);
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Saves the new treatment to the database using the {@link TreatmentDao}.
     *
     * @param treatment the treatment to be stored
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.create(treatment);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the "Cancel" button event and closes the current window without saving.
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }

    /**
     * Checks whether the user input is valid. Disables the "Add" button if:
     * - begin or end time is empty or invalid
     * - end time is not after begin time
     * - description is empty
     * - no date is selected
     *
     * @return <code>true</code> if any input data is invalid; <code>false</code> otherwise
     */
    private boolean areInputDataInvalid() {
        if (this.textFieldBegin.getText() == null || this.textFieldEnd.getText() == null) {
            return true;
        }
        try {
            LocalTime begin = DateConverter.convertStringToLocalTime(this.textFieldBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(this.textFieldEnd.getText());
            if (!end.isAfter(begin)) {
                return true;
            }
        } catch (Exception exception) {
            return true;
        }
        return this.textFieldDescription.getText().isBlank() || this.datePicker.getValue() == null;
    }
}