package sample.Controllers.SceneControllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;

public class AdminHomeScreenController extends Controller {
    @FXML
    private Button buttonLogOut;

    @FXML
    public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }
}

