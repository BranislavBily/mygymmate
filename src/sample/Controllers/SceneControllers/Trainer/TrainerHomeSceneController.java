package sample.Controllers.SceneControllers.Trainer;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.ProfileFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.SettingsFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Session;

import java.io.IOException;

public class TrainerHomeSceneController extends HomeSceneController {

    @FXML
    private Label labelUsername;

    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonTraineeInfo;
    @FXML
    private Button buttonAboutUs;

    private int userID;

    public void onCreate() {
        userID = Session.getUserID();
        setLabel();
    }

    private void setLabel() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        setLabelUsername(labelUsername, username);
    }

    @FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene());
    }

    @FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene());
    }

    @FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonDiet.getScene());
    }

    @FXML
    private void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @FXML
    private void onProfileButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.PROFILE_FRAGMENT));
        Parent fragment = null;
        try {
            fragment = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProfileFragmentController profileFragment = fxmlLoader.getController();
        profileFragment.onCreate();
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");
    }

    @FXML
    private void onSettingsButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.SETTINGS_FRAGMENT));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsFragmentController settingsFragment = fxmlLoader.getController();
        settingsFragment.onCreate();
        mainFragment.getChildren().setAll(root);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }

    @FXML
    private void onTraineeInfoButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.TRAINEE_INFO_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonTraineeInfo.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

