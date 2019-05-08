package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import sample.Controllers.SceneControllers.Controller;
import sample.Dialogs.PasswordConfirmation;
import sample.Dialogs.WrongPasswordDialog;
import sample.Session;

import java.util.Optional;

public class SettingsFragmentController extends Controller {

    @FXML
    private Label labelFeedback;
    @FXML
    private Label labelDatabaseError;
    @FXML
    private Label labelUsernameError;
    @FXML
    private Label labelFirstNameError;
    @FXML
    private Label labelLastNameError;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private ChoiceBox choiceBoxGender;
    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;
    @FXML
    private Button buttonDelete;

    private DatabaseModuleUser databaseModuleUser;

    /**
     * Prepares scene for use
     */
    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(Session.getUserID());
        fillControlsWithData(profileData);
    }

    /**
     * Fills controls with data from {@link ProfileData}
     *
     * @param profileData data source
     */
    private void fillControlsWithData(ProfileData profileData) {
        textFieldUsername.setText(profileData.getUsername());
        textFieldFirstName.setText(profileData.getFirstName());
        textFieldLastName.setText(profileData.getLastName());
        setChoiceBoxItems();
        choiceBoxGender.setValue(profileData.getGender());
        choiceBoxTypeOfTraining.setValue(profileData.getTypeOfTraining());
    }

    @FXML
    private void onButtonSavePressed() {
        resetFeedback();
        if(correctUserData()) {
            ProfileData profileData = loadProfileDataFromScene();
            if (databaseModuleUser.updateUser(profileData)) {
                System.out.println("Settings saved");
                labelFeedback.setVisible(true);
            } else {
                System.out.println("Error while updating User data");
                labelDatabaseError.setVisible(true);
            }
        }
    }

    /**
     * Checks all user input and returns if there was a bad input or not
     * @return {@code true} if there was no bad input found, {@code false} when there was a bad input
     */
    private boolean correctUserData() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText().trim();
        String firstName = textFieldFirstName.getText().trim();
        String lastName = textFieldLastName.getText().trim();
        boolean badInput = false;

        //Checks username textField
        if(username.isEmpty()) {
            System.out.println("Empty username");
            labelUsernameError.setText("Please fill your username!");
            labelUsernameError.setVisible(true);
            displayErrorFeedbackUsername(textFieldUsername);
            badInput = true;
        } else if (username.contains(" ")) {
            System.out.println("Space in username");
            labelUsernameError.setText("Please fill your username!");
            labelUsernameError.setVisible(true);
            badInput = true;
            displayErrorFeedbackUsername(textFieldUsername);
            //If he changed his username and its in the database so its taken
        } else if (!databaseModuleUser.getUsername().equals(username) && databaseModuleUser.isUsernameTaken(username)) {
            System.out.println("Username already taken");
            labelUsernameError.setText("Username already taken!");
            labelUsernameError.setVisible(true);
            badInput = true;
            displayErrorFeedbackUsername(textFieldUsername);
        }

        //Checks firstName textField
        if(firstName.isEmpty()) {
            System.out.println("Empty first name");
            labelFirstNameError.setText("Please enter your first name!");
            labelFirstNameError.setVisible(true);
            displayErrorFeedbackUsername(textFieldFirstName);
            badInput = true;
        } else if (firstName.contains(" ")) {
            System.out.println("Space in first name");
            labelFirstNameError.setText("Please enter your first name!");
            labelFirstNameError.setVisible(true);
            displayErrorFeedbackUsername(textFieldFirstName);
            badInput = true;
        }

        //Checks lastName textField
        if(lastName.isEmpty()) {
            System.out.println("Empty last name name");
            labelLastNameError.setText("Please enter your last name!");
            labelLastNameError.setVisible(true);
            displayErrorFeedbackUsername(textFieldLastName);
            badInput = true;
        } else if (lastName.contains(" ")) {
            System.out.println("Space in last name");
            labelLastNameError.setText("Please enter your first name!");
            labelLastNameError.setVisible(true);
            displayErrorFeedbackUsername(textFieldLastName);
            badInput = true;
        }
        return !badInput;
    }

    /**
     * Loads data from controls and saves it in the {@code ProfileInfo}
     *
     * @return {@code ProfileInfo} filled with data from controls
     */
    private ProfileData loadProfileDataFromScene() {
        ProfileData profileData = new ProfileData();
        profileData.setUsername(textFieldUsername.getText().trim());
        profileData.setFirstName(textFieldFirstName.getText().trim());
        profileData.setLastName(textFieldLastName.getText().trim());
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

    private void resetFeedback() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0);
        dropShadow.setColor(Color.color(0, 0, 0));
        textFieldUsername.setEffect(dropShadow);
        textFieldFirstName.setEffect(dropShadow);
        textFieldLastName.setEffect(dropShadow);
        labelFeedback.setVisible(false);
        labelDatabaseError.setVisible(false);
        labelUsernameError.setVisible(false);
        labelFirstNameError.setVisible(false);
        labelLastNameError.setVisible(false);
    }

    private void displayErrorFeedbackUsername(TextField textField){
        DropShadow dropShadowUsername = new DropShadow();
        dropShadowUsername.setRadius(10);
        dropShadowUsername.setColor(Color.color(1,0,0));
        textField.setEffect(dropShadowUsername);
    }
}
