package sample.Controllers.FragmentControllers;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.Controllers.SceneControllers.Controller;
import sample.Dialogs.PasswordConfirmation;
import sample.Dialogs.WrongPasswordDialog;
import sample.Session;

import java.util.Optional;

public class SettingsFragmentController extends Controller {

    private int userID;

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

    public void onCreate() {
        userID = Session.getUserID();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(userID);
        textFieldRealName.setText(profileData.getRealName());
        textFieldUsername.setText(profileData.getUsername());
        setChoiceBoxItems();
        choiceBoxGender.setValue(profileData.getGender());
        choiceBoxTypeOfTraining.setValue(profileData.getTypeOfTraining());
        choiceBoxStatus.setValue(profileData.getStatus());
    }

    @FXML
    private void onButtonSavePressed() {
        //Check for user input
        ProfileData profileData = new ProfileData();
        profileData.setRealName(textFieldRealName.getText());
        profileData.setUsername(textFieldUsername.getText());
        profileData.setStatus(choiceBoxStatus.getValue().toString());
        profileData.setGender(choiceBoxGender.getValue().toString());
        profileData.setTypeOfTraining(choiceBoxTypeOfTraining.getValue().toString());
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        databaseModuleUser.updateUser(profileData);
        System.out.println("Settings saved");
        onCreate();
    }

    @FXML
    private void onButtonDeletePressed() {
        String passwordFromDialog = getPasswordFromPasswordDialog();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        if(databaseModuleUser.isUser(username, passwordFromDialog) != null) {
            databaseModuleUser.deleteLoggedInUser();
            setSceneToLogin(buttonDelete.getScene());
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

    private String getPasswordFromPasswordDialog() {
        PasswordConfirmation passwordConfirmation = new PasswordConfirmation("Enter password",
                "Confirm this action by entering your password", "");
        Optional<String> result = passwordConfirmation.showAndWait();
        return result.orElse("");
    }

}
