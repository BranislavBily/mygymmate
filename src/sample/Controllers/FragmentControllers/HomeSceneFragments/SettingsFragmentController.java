package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Controllers.SceneControllers.Controller;
import sample.Dialogs.PasswordConfirmation;
import sample.Dialogs.WrongPasswordDialog;
import sample.Session;

import java.util.Optional;

public class SettingsFragmentController extends Controller {

    private int userID;

    @FXML
    private Label labelFeedback;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldRealName;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonDelete;

    /**
     * Method prepares scene for use, sets up choice boxed and fills data
     */
    public void onCreate() {
        userID = Session.getUserID();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(userID);
        fillControlsWithData(profileData);
    }

    private void fillControlsWithData(ProfileData profileData) {
        textFieldRealName.setText(profileData.getRealName());
        textFieldUsername.setText(profileData.getUsername());
        setChoiceBoxItems();
        choiceBoxGender.setValue(profileData.getGender());
        choiceBoxTypeOfTraining.setValue(profileData.getTypeOfTraining());
        choiceBoxStatus.setValue(profileData.getStatus());
    }

    @FXML
    private void onButtonSavePressed() {
        ProfileData profileData = loadProfileDataFromScene();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        if (databaseModuleUser.updateUser(profileData)) {
            System.out.println("Settings saved");
            labelFeedback.setVisible(true);
        } else {
            System.out.println("Error while updating User data");
        }
    }

    private ProfileData loadProfileDataFromScene() {
        ProfileData profileData = new ProfileData();
        profileData.setRealName(textFieldRealName.getText());
        profileData.setUsername(textFieldUsername.getText());
        profileData.setStatus(choiceBoxStatus.getValue().toString());
        profileData.setGender(choiceBoxGender.getValue().toString());
        profileData.setTypeOfTraining(choiceBoxTypeOfTraining.getValue().toString());
        return profileData;
    }

    @FXML
    private void onButtonDeletePressed() {
        String passwordFromDialog = getPasswordFromPasswordDialog();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        if (databaseModuleUser.isUser(username, passwordFromDialog) != null) {
            if (!databaseModuleUser.deleteLoggedInUser()) {
                System.out.println("Error while deleting user");
            } else {
                setSceneToLogin(buttonDelete.getScene());
            }
        } else {
            new WrongPasswordDialog(Alert.AlertType.ERROR);
            System.out.println("Wrong password");
        }
    }

    private void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "Male", "Female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose Weight", "Gain Muscle")
        );

        choiceBoxStatus.setItems(FXCollections.observableArrayList(
                "Trainee", "Trainer")
        );
    }

    /**
     * Creates new PasswordConfirmation dialog and returns user input or empty {@code String}
     * @return User input {@code String} or empty {@code String} if user cancelled dialog
     */
    private String getPasswordFromPasswordDialog() {
        PasswordConfirmation passwordConfirmation = new PasswordConfirmation("Enter password",
                "Confirm this action by entering your password", "");
        Optional<String> result = passwordConfirmation.showAndWait();
        return result.orElse("");
    }
}
