package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Resources.ResourceFXML;
import sample.Session;

import java.io.IOException;

public class ProfileFragmentController {
    @FXML
    private Label labelName;
    @FXML
    private Label labelGender;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelTypeOfTraining;

    private DatabaseModuleUser databaseModuleUser;

    /**
     * Method prepares scene for use, fills it with profile data
     */
    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        loadProfileInfo();
    }

    /**
     * Loads profile info from the database and sets the data into controls
     */
    private void loadProfileInfo() {
        ProfileData profileData = databaseModuleUser.getUserProfileData();
        labelName.setText(profileData.getRealName());
        labelGender.setText(profileData.getGender());
        labelStatus.setText(profileData.getStatus());
        labelTypeOfTraining.setText(profileData.getTypeOfTraining());
    }

    @FXML
    private void onButtonChangePasswordPressed() {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.CHANGE_PASSWORD));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Password change");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonCheckPasswordPressed() {
        System.out.println("Check password");
    }
}
