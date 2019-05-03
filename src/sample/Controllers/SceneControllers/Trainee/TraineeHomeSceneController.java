package sample.Controllers.SceneControllers.Trainee;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.HomeFragmentController;
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
    private Button buttonHome;
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

    /**
     * Prepares scene for use
     */
    public void onCreate() {
        userID = Session.getUserID();
        setLabel();
    }

    /**
     * Sets username of logged in user into {@code Label}
     */
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

    /**
     * Sets {@code mainFragment} container into {@code ProfileFragment}
     */

    @FXML
    private void onHomeButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.HOME_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            HomeFragmentController homeFragment = fxmlLoader.getController();
            homeFragment.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome ,buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
            buttonHome.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onProfileButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.PROFILE_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            ProfileFragmentController profileFragment = fxmlLoader.getController();
            profileFragment.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome ,buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
            buttonProfile.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@code mainFragment} container info {@code SettingsFragment}
     */
    @FXML
    private void onSettingsButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.SETTINGS_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            SettingsFragmentController settingsFragment = fxmlLoader.getController();
            settingsFragment.onCreate();

            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome ,buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
            buttonSettings.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@code mainFragment} container into {@code TrainerInfoFragment}
     */
    @FXML
    private void onTrainerInfoButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.TRAINER_INFO_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            TrainerInfoFragmentController trainerInfoFragmentController = fxmlLoader.getController();

            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome ,buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
            buttonTrainerInfo.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@code mainFragment} container into {@code AboutUsFragment }
     */
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}



