package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;


public class UserHomeScreenController extends Controller {

    @FXML
    private Button buttonLogOut;

    @FXML
    private void onButtonLogOutPressed() {
        setScene(buttonLogOut.getScene(), ModulFXML.LOGIN, ModulTitles.LOG_IN);
    }
}
