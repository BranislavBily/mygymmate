package sample.Controllers.SceneControllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Users.Admin.Admin;

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

    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Label getLabelUsername() {
        return labelUsername;
    }

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
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


