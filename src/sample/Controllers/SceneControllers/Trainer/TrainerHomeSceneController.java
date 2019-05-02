package sample.Controllers.SceneControllers.Trainer;

import db.DatabaseModuleUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.ProfileFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.SettingsFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.FXML;
import sample.Session;

import java.io.IOException;

public class TrainerHomeSceneController extends HomeSceneController {

    @javafx.fxml.FXML
    private Label labelUsername;

    @javafx.fxml.FXML
    private Button buttonLogOut;
    @javafx.fxml.FXML
    private Button buttonWorkout;
    @javafx.fxml.FXML
    private Button buttonDiet;
    @javafx.fxml.FXML
    private Button buttonMeasure;
    @javafx.fxml.FXML
    private AnchorPane mainFragment;
    @javafx.fxml.FXML
    private Button buttonProfile;
    @javafx.fxml.FXML
    private Button buttonSettings;
    @javafx.fxml.FXML
    private Button buttonTraineeInfo;
    @javafx.fxml.FXML
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

    @javafx.fxml.FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene());
    }

    @javafx.fxml.FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene());
    }

    @javafx.fxml.FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonDiet.getScene());
    }

    @javafx.fxml.FXML
    private void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @javafx.fxml.FXML
    private void onProfileButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML.PROFILE_FRAGMENT));
        Parent fragment = null;
        try {
            fragment = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProfileFragmentController profileFragment = fxmlLoader.getController();
        profileFragment.loadProfileInfo();
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");
    }

    @javafx.fxml.FXML
    private void onSettingsButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML.SETTINGS_FRAGMENT));
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

    @javafx.fxml.FXML
    private void onTraineeInfoButtonClicked() {
        Parent fragment= loadFragmentFromFXML(FXML.TRAINEE_INFO_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonTraineeInfo.getStyleClass().add("buttonActive");
    }
    @javafx.fxml.FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(FXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

