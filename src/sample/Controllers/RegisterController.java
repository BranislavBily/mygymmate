package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;

public class RegisterController extends Controller {

    @FXML
    private Hyperlink hyperLinkAlreadyMember;


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModulTitles.LOG_IN);
    }
}
