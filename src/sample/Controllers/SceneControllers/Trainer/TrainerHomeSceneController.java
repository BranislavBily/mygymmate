package sample.Controllers.SceneControllers.Trainer;

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
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import java.io.IOException;

public class TrainerHomeSceneController extends HomeSceneController {

    @FXML
    private Label labelUsername;

    @FXML
    private Button buttonLogOut;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonTraineeInfo;
    @FXML
    private Button buttonAboutUs;

    private DatabaseModuleUser databaseModuleUser;

    /**
     * Prepares scene for use
     */
    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        setLabel();
        onHomeButtonClicked();
    }

    /**
     * Sets username of logged in user into {@code Label}
     */
    private void setLabel() {
        String username = databaseModuleUser.getUsername();
        setLabelUsername(labelUsername, username);
    }

    /**
     * Logs out
     */
    @FXML
    private void onButtonLogOutPressed() {
        onButtonLogOutPressed(buttonLogOut);
    }

    /**
     * Sets {@code mainFragment} container into {@code HomeFragment}
     */
    @FXML
    private void onHomeButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.HOME_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            HomeFragmentController homeFragment = fxmlLoader.getController();
            homeFragment.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome ,buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
            buttonHome.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@code mainFragment} container in {@code ProfileFragment}
     */
    @FXML
    private void onProfileButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.PROFILE_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            ProfileFragmentController profileFragment = fxmlLoader.getController();
            profileFragment.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonHome, buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
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
            removeButtonActiveEffect(buttonHome, buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
            buttonSettings.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@code mainFragment} container into {@code TrainerInfoFragment}
     */
    @FXML
    private void onTraineeInfoButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.TRAINEE_INFO_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonHome, buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonTraineeInfo.getStyleClass().add("buttonActive");
    }

    /**
     * Sets {@code mainFragment} container into {@code AboutUsFragment}
     */
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonHome , buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

