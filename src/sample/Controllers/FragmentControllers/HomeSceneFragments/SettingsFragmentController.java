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
    private Label labelEmpty;
    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldRealName;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonDelete;

    /**
     * Prepares scene for use
     */
    public void onCreate() {
        userID = Session.getUserID();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(userID);
        fillControlsWithData(profileData);
    }

    /**
     * Fills controls with data from {@link ProfileData}
     *
     * @param profileData data source
     */
    private void fillControlsWithData(ProfileData profileData) {
        textFieldRealName.setText(profileData.getRealName());
        textFieldUsername.setText(profileData.getUsername());
        setChoiceBoxItems();
        choiceBoxGender.setValue(profileData.getGender());
        choiceBoxTypeOfTraining.setValue(profileData.getTypeOfTraining());
    }

    @FXML
    private void onButtonSavePressed() {
        ProfileData profileData = loadProfileDataFromScene();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        if (profileData.chceckEmpty() && databaseModuleUser.updateUser(profileData)) {

            System.out.println("Settings saved");
            labelFeedback.setVisible(true);
            labelEmpty.setVisible(false);
        } else {
            System.out.println("Error while updating User data");
            labelEmpty.setVisible(true);
            labelFeedback.setVisible(false);
        }
    }

    /**
     * Loads data from controls and saves it in the {@code ProfileInfo}
     *
     * @return {@code ProfileInfo} filled with data from controls
     */
    private ProfileData loadProfileDataFromScene() {
        ProfileData profileData = new ProfileData();
        profileData.setRealName(textFieldRealName.getText());
        profileData.setUsername(textFieldUsername.getText());
        profileData.setGender(choiceBoxGender.getValue().toString());
        profileData.setTypeOfTraining(choiceBoxTypeOfTraining.getValue().toString());
        return profileData;
    }

    /**
     * Gets password from {@code PasswordConfirmation} dialog
     */
    @FXML
    private void onButtonDeletePressed() {
        String passwordFromDialog = getPasswordFromPasswordDialog();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        if (databaseModuleUser.correctUserPassword(passwordFromDialog)) {
            if (!databaseModuleUser.deleteLoggedInUser()) {
                System.out.println("Error while deleting user");
            } else {
                setSceneToLogin(buttonDelete.getScene());
            }
        } else if (!passwordFromDialog.isEmpty()) {
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
    }

    /**
     * Creates new PasswordConfirmation dialog and returns user input or empty {@code String}
     *
     * @return User input {@code String} or empty {@code String} if user cancelled dialog
     */
    private String getPasswordFromPasswordDialog() {
        PasswordConfirmation passwordConfirmation = new PasswordConfirmation("Enter password",
                "Confirm this action by entering your password", "");
        Optional<String> result = passwordConfirmation.showAndWait();
        return result.orElse("");
    }
}
