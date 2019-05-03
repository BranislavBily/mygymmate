package sample.Controllers.SceneControllers.Admin;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.HomeSceneFragments.HomeAdminFragmentController;
import sample.Controllers.FragmentControllers.HomeSceneFragments.HomeFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Session;

import java.io.IOException;

public class AdminHomeSceneController extends HomeSceneController {

    @FXML
    private Label labelUsername;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonAboutUs;
    @FXML
    private Button buttonLogOut;


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

    public Label getLabelUsername() {
        return labelUsername;
    }

    @FXML
    public void onButtonLogOutPressed() {
        onButtonLogOutPressed(buttonLogOut);
    }

    @FXML
    private void onHomeButtonClicked() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.HOME_ADMIN_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            HomeAdminFragmentController homeAdminFragment = fxmlLoader.getController();
            homeAdminFragment.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeAdminButtonActiveEffect(buttonHome , buttonProfile, buttonSettings, buttonAboutUs);
            buttonHome.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onProfileButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonHome , buttonProfile, buttonSettings, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");

    }
    @FXML
    private void onSettingsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonHome , buttonProfile, buttonSettings, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ResourceFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonHome , buttonProfile, buttonSettings, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

