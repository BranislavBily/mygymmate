package sample.Controllers.SceneControllers.Admin;

import db.DatabaseModuleUser;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.FXML;
import sample.Session;

public class AdminHomeSceneController extends HomeSceneController {

    @javafx.fxml.FXML
    private Label labelUsername;
    @javafx.fxml.FXML
    private AnchorPane mainFragment;
    @javafx.fxml.FXML
    private Button buttonProfile;
    @javafx.fxml.FXML
    private Button buttonSettings;
    @javafx.fxml.FXML
    private Button buttonAboutUs;
    @javafx.fxml.FXML
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

    @javafx.fxml.FXML
    public void onButtonLogOutPressed() {
        onButtonLogOutPressed(buttonLogOut);
    }

    @javafx.fxml.FXML
    private void onProfileButtonClicked() {
        Parent fragment= loadFragmentFromFXML(FXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");

    }
    @javafx.fxml.FXML
    private void onSettingsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(FXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @javafx.fxml.FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(FXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

