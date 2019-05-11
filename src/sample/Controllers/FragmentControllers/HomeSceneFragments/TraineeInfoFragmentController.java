package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DTO.ProfileData;
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
import sample.Controllers.PopUpWindowControllers.AddTraineeController;
import sample.Controllers.PopUpWindowControllers.TraineeInfoController;
import sample.Dialogs.DeleteTraineeDialog;
import sample.Resources.ResourceFXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TraineeInfoFragmentController {

    @FXML
    private TableView<ProfileData> tableViewTrainees;
    @FXML
    private TableColumn<ProfileData, String> tableColumnFirstName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnLastName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnTypeOfTraining;

    private DatabaseModuleInfo databaseModuleInfo;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.ADD_TRAINEE));
        try {
            Parent root = loader.load();
            AddTraineeController addTraineeController = loader.getController();
            addTraineeController.onCreate();
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
                if(databaseModuleInfo.deleteTraineeFromTrainer(profileData.getUsername())) {
                    loadTraineesIntoTable();
                    System.out.println("Success");
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
}
