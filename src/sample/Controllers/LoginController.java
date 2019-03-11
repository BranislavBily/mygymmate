package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController extends Controller {

    @FXML
    private Button buttonLogIn;

    @FXML
    private void onButtonLogInPressed() {
        setScene(buttonLogIn.getScene(), "registration.fxml", "Welcome");
    }
}
