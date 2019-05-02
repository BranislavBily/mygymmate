package sample.Controllers.SceneControllers.Trainee;

import db.DatabaseModuleUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.ProfileFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.SettingsFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.TrainerInfoFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.FXML;
import sample.Session;

import java.io.IOException;

public class TraineeHomeSceneController extends HomeSceneController {

    @javafx.fxml.FXML
    private Button buttonLogOut;
    @javafx.fxml.FXML
    private Button buttonWorkout;
    @javafx.fxml.FXML
    private Button buttonDiet;
    @javafx.fxml.FXML
    private Button buttonWithMeasure;
    @javafx.fxml.FXML
    private AnchorPane mainFragment;
    @javafx.fxml.FXML
    private Button buttonProfile;
    @javafx.fxml.FXML
    private Button buttonSettings;
    @javafx.fxml.FXML
    private Button buttonTrainerInfo;
    @javafx.fxml.FXML
    private Button buttonAboutUs;
    @javafx.fxml.FXML
    private Label labelUsername;
    @javafx.fxml.FXML
    private Label labelQuote;

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
    public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @javafx.fxml.FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene());
    }

    @javafx.fxml.FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonWithMeasure.getScene());
    }

    @javafx.fxml.FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene());
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
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
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
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @javafx.fxml.FXML
    private void onTrainerInfoButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML.TRAINER_INFO_FRAGMENT));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TrainerInfoFragmentController trainerInfoFragmentController = fxmlLoader.getController();

        mainFragment.getChildren().setAll(root);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonTrainerInfo.getStyleClass().add("buttonActive");
    }

    @javafx.fxml.FXML
    private void onAboutUsButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML.ABOUT_US_FRAGMENT));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainFragment.getChildren().setAll(root);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}



