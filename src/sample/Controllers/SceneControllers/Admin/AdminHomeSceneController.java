package sample.Controllers.SceneControllers.Admin;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleFXML;

public class AdminHomeSceneController extends HomeSceneController {

    @FXML
    private Label labelUsername;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonAboutUs;
    @FXML
    private Button buttonLogOut;


    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLabel() {
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
    private void onProfileButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onSettingsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeAdminButtonActiveEffect(buttonProfile, buttonSettings, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}


