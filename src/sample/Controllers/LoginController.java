package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;

public class LoginController extends Controller {

    @FXML
    private Button buttonLogIn;

    @FXML
    private void onButtonLogInPressed() {
        setScene(buttonLogIn.getScene(), ModulFXML.REGISTER, ModulTitles.REGISTER);
    }
}
