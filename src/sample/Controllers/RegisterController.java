package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModuleTitles;
import sample.User;

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
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();
        if (databaseModuleUser.isUsernameTaken(username)) {
            //Sprava ked je meno uz obsadene
            System.out.println("Username taken");
        } else if (!password.equals(passwordAgain)) {
            //Sprava ked sa hesla nerovnaju
            System.out.println("Passwords do not match");
        } else {
            User user = new User(username, password);
            setScene(textFieldUsername.getScene(), ModulFXML.REGISTER_INFO, user);
        }
    }


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModuleTitles.LOG_IN);
    }

}
