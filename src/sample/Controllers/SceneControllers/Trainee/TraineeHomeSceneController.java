package sample.Controllers.SceneControllers.Trainee;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.ProfileFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.SettingsFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.TrainerInfoFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Session;

import java.io.IOException;

public class TraineeHomeSceneController extends HomeSceneController {

    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonWithMeasure;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonTrainerInfo;
    @FXML
    private Button buttonAboutUs;
    @FXML
    private Label labelUsername;
    @FXML
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

    @FXML
    public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene());
    }

    @FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonWithMeasure.getScene());
    }

    @FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene());
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
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
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
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onTrainerInfoButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.TRAINER_INFO_FRAGMENT));
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

    @FXML
    private void onAboutUsButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.ABOUT_US_FRAGMENT));
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



