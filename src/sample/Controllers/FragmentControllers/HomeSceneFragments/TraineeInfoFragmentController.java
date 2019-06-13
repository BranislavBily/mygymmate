package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DTO.ProfileData;
import db.DTO.User;
import db.DatabaseModuleInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.PopUpWindowControllers.SearchForTraineeController;
import sample.Controllers.PopUpWindowControllers.TraineeInfoController;
import sample.Controllers.SceneControllers.Controller;
import sample.Controllers.SceneControllers.LoginRegister.LoginController;
import sample.Dialogs.DeleteTraineeDialog;
import sample.Resources.ResourceFXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TraineeInfoFragmentController extends Controller {

    @FXML
    private TableView<ProfileData> tableViewTrainees;
    @FXML
    private TableColumn<ProfileData, String> tableColumnFirstName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnLastName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnTypeOfTraining;
    @FXML
    private TableColumn<ProfileData, String> tableColumnUsername;

    private DatabaseModuleInfo databaseModuleInfo;
    private String TrainerUsername = LoginController.LoggedUserName;
    private String TrainerEmail=LoginController.LoggedUserEmail;


    public void onCreate() {
        databaseModuleInfo = new DatabaseModuleInfo();
        loadTraineesIntoTable();
        setTableViewOnDoubleClickListener();

    }

    private void setTableViewOnDoubleClickListener() {
        tableViewTrainees.setRowFactory(table -> {
            TableRow<ProfileData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    ProfileData profileData = row.getItem();
                    openTraineeInfoScene(profileData.getId());

                }
            });
            return row;
        });
    }

    private void loadTraineesIntoTable() {
        ArrayList<ProfileData> trainees = databaseModuleInfo.getTraineesOfLoggedTrainer();
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory("Username"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory("FirstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory("LastName"));
        tableColumnTypeOfTraining.setCellValueFactory(new PropertyValueFactory("TypeOfTraining"));
        ObservableList<ProfileData> profileDataObservableList = FXCollections.observableList(trainees);
        tableViewTrainees.setItems(profileDataObservableList);
    }

    private void openTraineeInfoScene(int traineeID) {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.TRAINEE_INFO));
        try {
            Parent root = loader.load();
            TraineeInfoController traineeInfoController = loader.getController();
            traineeInfoController.onCreate(traineeID);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TraineeInfo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonAddTraineePressed() {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.SEARCH_FOR_TRAINEE));
        try {
            Parent root = loader.load();
            SearchForTraineeController searchForTraineeController = loader.getController();
            searchForTraineeController.onCreate();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TraineeInfo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadTraineesIntoTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuItemDeletePressed() {
        ProfileData profileData = tableViewTrainees.getSelectionModel().getSelectedItem();
        if(profileData == null) {
            System.out.println("Prazdny klik");
        } else {
            boolean deleteTrainee = getDeleteTraineeDialogAnswer(profileData);
            if(deleteTrainee) {
                if(databaseModuleInfo.deleteTraineeFromTrainer(profileData.getId())) {
                    loadTraineesIntoTable();
                    System.out.println("Success");
                    String email = databaseModuleInfo.getTraineeEmailFromDbByUsername(profileData.getUsername());
                    addingNotification(email);


                } else {
                    System.out.println("Error while deleting Trainee from Trainer");
                }
            } else {
                System.out.println("Vypol sa dialog");
            }
        }

    }

    private boolean getDeleteTraineeDialogAnswer(ProfileData profileData) {
        DeleteTraineeDialog deleteTraineeDialog = new DeleteTraineeDialog(Alert.AlertType.CONFIRMATION, profileData);
        Optional<ButtonType> result = deleteTraineeDialog.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    protected void addingNotification(String email){
        String subject = "Trainer Notification";
        String content ="<div><b> Hi, you have been deleted from "+TrainerUsername+"´s training group by <strong style=\"color:blue;\">"+TrainerUsername+"</strong>.<br> If you have any questions contact him by email : <strong style=\"color:blue;\">"+TrainerEmail+"</strong></b></div>";
        sendEmail(email,subject,content);
    }
}
