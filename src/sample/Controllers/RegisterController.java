package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class RegisterController extends Controller {

    @FXML
    private Hyperlink hyperLinkAlreadyMember;


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), "login.fxml", "Log in");
    }
}
