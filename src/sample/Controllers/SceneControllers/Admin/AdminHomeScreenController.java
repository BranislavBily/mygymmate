package sample.Controllers.SceneControllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Users.Admin.Admin;

public class AdminHomeScreenController extends Controller {

    @FXML
    private Label labelUsername;
    @FXML
    private AnchorPane mainFragment;
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
        super.onButtonLogOutPressed(buttonLogOut);
    }


    @FXML
    private void onProfileButtonClicked() {
        Parent fragment= Fragment(ModuleFXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
    }
    @FXML
    private void onSettingsButtonClicked() {
        Parent fragment= Fragment(ModuleFXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= Fragment(ModuleFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
    }


}


