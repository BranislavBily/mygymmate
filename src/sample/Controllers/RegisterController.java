package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;

public class RegisterController extends Controller {

    @FXML
    private Hyperlink hyperLinkAlreadyMember;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private PasswordField passwordFieldPasswordAgain;

    @FXML
    private void onButtonSignUpPressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();

    }


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModulTitles.LOG_IN);
    }

}
