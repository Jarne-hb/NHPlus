package de.hitec.nhplus.presenter;

import de.hitec.nhplus.datastorage.CaregiverDao;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Caregiver;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The <code>TreatmentPresenter</code> contains the entire logic of the treatment view. It determines which data is displayed and how to react to events.
 */
public class TreatmentPresenter {

    @FXML
    private Label labelPatientName;

    @FXML
    private Label labelCareLevel;

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

    private AllTreatmentPresenter controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;
    private Caregiver caregiver;

    /**
     * When <code>initialize()</code> gets called, all fields are already initialized. For example from the FXMLLoader
     * after loading an FXML-File. At this point of the lifecycle of the Presenter, the fields can be accessed and
     * configured.
     */
    public void initialize(AllTreatmentPresenter controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller= controller;
        PatientDao pDao = DaoFactory.getDaoFactory().createPatientDAO();
        CaregiverDao cgDao = DaoFactory.getDaoFactory().createCaregiverDao();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.caregiver = cgDao.read(treatment.getCgID());
            this.treatment = treatment;
            showData();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Displays existing treatment, patient, and caregiver data in the corresponding form fields. <br>
     */
    private void showData(){
        this.labelPatientName.setText(patient.getSurname() + ", " + patient.getFirstName());
        this.labelCareLevel.setText(patient.getCareLevel());
        this.labelCaregiverName.setText(caregiver.getSurname() + ", " + caregiver.getFirstName());
        this.labelCaregiverTelephone.setText(caregiver.getTelephone());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datePicker.setValue(date);
        this.textFieldBegin.setText(this.treatment.getBegin());
        this.textFieldEnd.setText(this.treatment.getEnd());
        this.textFieldDescription.setText(this.treatment.getDescription());
        this.textAreaRemarks.setText(this.treatment.getRemarks());
    }

    /**
     * Handles the update of an existing treatment based on the input fields. <br>
     * <br>
     * This method collects the modified treatment data from the form,
     * updates the corresponding {@link Treatment} object, persists the changes
     * to the database, refreshes the treatment table in the main view,
     * and closes the current window.
     */
    @FXML
    public void handleChange(){
        this.treatment.setDate(this.datePicker.getValue().toString());
        this.treatment.setBegin(textFieldBegin.getText());
        this.treatment.setEnd(textFieldEnd.getText());
        this.treatment.setDescription(textFieldDescription.getText());
        this.treatment.setRemarks(textAreaRemarks.getText());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Updates the current treatment in the database. <br>
     * <br>
     * This method uses the {@link TreatmentDao} to persist the changes
     * made to the {@link Treatment} object. If a {@link SQLException} occurs,
     * it will be printed to the console.
     */
    private void doUpdate(){
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.update(treatment);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the cancel action by closing the current window.
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }
}