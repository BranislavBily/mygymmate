package sample.Controllers.PopUpWindowControllers;

import db.DTO.ProfileData;
import db.DatabaseModuleInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Dialogs.AddTraineeDialog;
import sample.Dialogs.DeleteTraineeDialog;

import java.util.ArrayList;
import java.util.Optional;

public class AddTraineeController {

    @FXML
    private TableView<ProfileData> tableViewTrainees;
    @FXML
    private TableColumn<ProfileData, String> tableColumnUsername;
    @FXML
    private TableColumn<ProfileData, String> tableColumnFirstName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnLastName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnTypeOfTraining;
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelSuccess;

    private DatabaseModuleInfo databaseModuleInfo;

    public void onCreate() {
        databaseModuleInfo = new DatabaseModuleInfo();
        setTableViewOnDoubleClickListener();
        setTextFieldNameOnChangeListener();
    }

    private void setTableViewOnDoubleClickListener() {
        tableViewTrainees.setPlaceholder(new Label("No trainees found!"));
        tableViewTrainees.setRowFactory(table -> {
            TableRow<ProfileData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ProfileData profileData = row.getItem();
                    boolean addTrainee = getAddTraineeDialogAnswer(profileData);
                    if(addTrainee) {
                        if(databaseModuleInfo.addTraineeToTrainer(profileData.getUsername())) {
                            labelSuccess.setVisible(true);
                            System.out.println("Trainee added");
                            textFieldName.setText("");
                        } else {
                            System.out.println("Error occurred when adding user");
                        }
                    } else {
                        //Do nothing
                        System.out.println("Nothing");
                    }
                }
            });
            return row;
        });
    }

    private void setTextFieldNameOnChangeListener() {
        textFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()) {
                tableViewTrainees.getItems().clear();
            } else {
                labelSuccess.setVisible(false);
                String[] name = newValue.split(" ");
                ArrayList<ProfileData> trainees;
                try {
                    trainees = databaseModuleInfo.getAllTraineesByName(name[0].concat("%"), name[1].concat("%"));
                } catch (ArrayIndexOutOfBoundsException e) {
                    trainees = databaseModuleInfo.getAllTraineesByName(name[0].concat("%"));
                }
                loadTraineesIntoTable(trainees);
            }
        });
    }

    private void loadTraineesIntoTable(ArrayList<ProfileData> trainees) {
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory("Username"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory("FirstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory("LastName"));
        tableColumnTypeOfTraining.setCellValueFactory(new PropertyValueFactory("TypeOfTraining"));
        ObservableList<ProfileData> profileDataObservableList = FXCollections.observableList(trainees);
        tableViewTrainees.setItems(profileDataObservableList);
    }

    private boolean getAddTraineeDialogAnswer(ProfileData profileData) {
        AddTraineeDialog addTraineeDialog = new AddTraineeDialog(Alert.AlertType.CONFIRMATION, profileData.getRealName());
        Optional<ButtonType> result = addTraineeDialog.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
