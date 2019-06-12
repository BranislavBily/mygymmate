package sample.Controllers.PopUpWindowControllers;

import db.DTO.ProfileData;
import db.DTO.User;
import db.DatabaseModuleInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Controllers.SceneControllers.Controller;
import sample.Controllers.SceneControllers.LoginRegister.LoginController;
import sample.Controllers.SceneControllers.LoginRegister.RegisterController;
import sample.Controllers.SceneControllers.LoginRegistrationController;
import sample.Dialogs.AddTraineeDialog;

import java.util.ArrayList;
import java.util.Optional;

public class SearchForTraineeController extends Controller {

    private String TrainerEmail=LoginController.LoggedUserEmail;
    private String TrainerUsername = LoginController.LoggedUserName;


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
                    boolean deleteConfirmed = getAddTraineeDialogAnswer(profileData.getRealName());
                    if(deleteConfirmed) {
                        if(databaseModuleInfo.addTraineeToTrainer(profileData.getId())) {
                            labelSuccess.setVisible(true);
                            System.out.println("Trainee added");
                            //If trainee added, clears table
                            textFieldName.setText("");
                            String email = databaseModuleInfo.getTraineeEmailFromDbByUsername(profileData.getUsername());
                            addingNotification(email);

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
                //If user entered first and last name
                try {
                    trainees = databaseModuleInfo.getAllTraineesByName(name[0].concat("%"), name[1].concat("%"));
                } catch (ArrayIndexOutOfBoundsException e) {
                    //If he only entered first name
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

    /**
     * Opens up a dialog and returns {@code true} if user confirmed action, {@code false} if not
     * @param name - Name of the trainee that is to be deleted
     * @return {@code true} if user confirmed action, {@code false} if he did not
     */
    private boolean getAddTraineeDialogAnswer(String name) {
        AddTraineeDialog addTraineeDialog = new AddTraineeDialog(Alert.AlertType.CONFIRMATION, name);
        Optional<ButtonType> result = addTraineeDialog.showAndWait();
        return result.get() == ButtonType.OK;
    }
    @Override
    protected void addingNotification(String email){
        String subject = "Trainer Notification";
        String content ="<div><b> Hi , the trainer <strong style=\"color:blue;\">"+TrainerUsername+"</strong> was added you into his training group.<br> If you have some questions contact him by email : <strong style=\"color:blue;\">"+TrainerEmail+"</strong></b></div>";
        sendEmail(email,subject,content);
    }


}
