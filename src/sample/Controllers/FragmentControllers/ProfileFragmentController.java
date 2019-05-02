package sample.Controllers.FragmentControllers;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Session;

public class ProfileFragmentController {

    @FXML
    private Label labelName;
    @FXML
    private Label labelGender;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelTypeOfTraining;
    @FXML
    private Button buttonEditProfile;
    @FXML
    private Button buttonChangePassword;
    @FXML
    private Button buttonCheckPassword;
    @FXML
    private Button buttonLoad;

    @FXML
    public void onButtonLoadPressed() {

    }

    public void loadProfileInfo() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(Session.getUserID());
        labelName.setText(profileData.getRealName());
        labelGender.setText(profileData.getGender());
        labelStatus.setText(profileData.getStatus());
        labelTypeOfTraining.setText(profileData.getTypeOfTraining());
    }

    @FXML
    private void onButtonEditProfilePressed() {
        System.out.println("Edit profile");
    }

    @FXML
    private void onButtonChangePasswordPressed() {
        System.out.println("Change password");
    }

    @FXML
    private void onButtonCheckPasswordPressed() {
        System.out.println("Check password");
    }
}
