package sample.Controllers.SceneControllers.Trainee;

import db.DatabaseModuleInfo;
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
import sample.Interfaces.HomeScene;
import sample.Resources.ResourceFXML;
import java.io.IOException;

public class TraineeHomeSceneController extends HomeSceneController implements HomeScene {

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
    private Button buttonTrainerInfo;
    @FXML
    private Button buttonAboutUs;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelQuote;

    private DatabaseModuleUser databaseModuleUser;
    private DatabaseModuleInfo databaseModuleInfo;

    /**
     * Prepares scene for use
     */
    @Override
    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        databaseModuleInfo = new DatabaseModuleInfo();
        setLabel();
        onHomeButtonClicked();
    }

    /**
     * Sets username of logged in user into {@code Label}
     */
    @Override
    public void setLabel() {
        String username = databaseModuleUser.getUsername();
        setLabelUsername(labelUsername, username);
    }

    @FXML
    @Override
    public void onButtonLogOutPressed() {
        onButtonLogOutPressed(buttonLogOut);
    }


    /**
     * Sets {@code mainFragment} container into {@code HomeFragment}
     */
    @FXML
    @Override
    public void onHomeButtonClicked() {
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
    /**
     * Sets {@code mainFragment} container into {@code ProfileFragment}
     */
    @FXML
    @Override
    public void onProfileButtonClicked() {
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
    @Override
    public void onSettingsButtonClicked() {
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
            trainerInfoFragmentController.onCreate();
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
    @Override
    public void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonHome, buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}



