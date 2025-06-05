package de.hitec.nhplus.presenter;

import de.hitec.nhplus.datastorage.CaregiverDao;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.model.Caregiver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;

public class AllCaregiverPresenter {

    @FXML
    private TableView<Caregiver> tableView;

    @FXML
    private TableColumn<Caregiver, Integer> colID;

    @FXML
    private TableColumn<Caregiver, String> colSurname;

    @FXML
    private TableColumn<Caregiver, String> colFirstName;

    @FXML
    private TableColumn<Caregiver, String> colTelephone;

    @FXML
    private TextField txfSurname;

    @FXML
    private TextField txfFirstname;

    @FXML
    private TextField txfTelephone;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    private final ObservableList<Caregiver> caregivers = FXCollections.observableArrayList();
    private CaregiverDao dao;

    @FXML
    private void initialize(){
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("cgID"));

        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableView.setItems(this.caregivers);

        this.btnDelete.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Caregiver>() {
            @Override
            public void changed(ObservableValue<? extends Caregiver> observableValue, Caregiver oldCaregiver, Caregiver newCaregiver) {;
                AllCaregiverPresenter.this.btnDelete.setDisable(newCaregiver == null);
            }
        });

        this.btnAdd.setDisable(true);
        ChangeListener<String> inputNewCaregiverListener = (observableValue, oldText, newText) ->
                AllCaregiverPresenter.this.btnAdd.setDisable(!AllCaregiverPresenter.this.areInputDataValid());
        this.txfSurname.textProperty().addListener(inputNewCaregiverListener);
        this.txfFirstname.textProperty().addListener(inputNewCaregiverListener);
        this.txfTelephone.textProperty().addListener(inputNewCaregiverListener);
    }

    private void readAllAndShowInTableView() {
        this.caregivers.clear();
        this.dao = DaoFactory.getDaoFactory().createCaregiverDao();
        try {
            this.caregivers.addAll(this.dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setSurname(event.getNewValue());
        this.doUpdate(event);
    }

    @FXML
    public void handleOnEditFirstName(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setFirstName(event.getNewValue());
        this.doUpdate(event);
    }

    @FXML
    public void handleOnEditTelephone(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setTelephone(event.getNewValue());
        this.doUpdate(event);
    }

    private boolean areInputDataValid() {
        return !this.txfFirstname.getText().isBlank() && !this.txfSurname.getText().isBlank() && !this.txfTelephone.getText().isBlank();
    }

    @FXML
    public void handleDelete(){
        Caregiver caregiverSelection = tableView.getSelectionModel().getSelectedItem();
        if (caregiverSelection != null) {
            try {
                DaoFactory.getDaoFactory().createCaregiverDao().deleteById(caregiverSelection.getCgID());
                this.tableView.getItems().remove(caregiverSelection);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @FXML
    public void handleAdd(){
        String surname = txfSurname.getText();
        String firstName = txfFirstname.getText();
        String telephone = txfTelephone.getText();

        try {
            this.dao.create(new Caregiver(surname, firstName, telephone, true));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextFields();
    }

    private void clearTextFields(){
        txfSurname.clear();
        txfFirstname.clear();
        txfTelephone.clear();
    }

    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> event) {
        try {
            this.dao.update(event.getRowValue());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
