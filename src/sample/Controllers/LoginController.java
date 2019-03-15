package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class LoginController extends Controller {

    @FXML
    private TextField textFieldUserName;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private void onButtonLogInPressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUserName.getText();
        String password = passwordFieldPassword.getText();
        if(databaseModuleUser.isUser(username, password)) {
            setScene(buttonLogIn.getScene(), ModulFXML.USER_HOME_SCREEN, ModulTitles.USER_HOME_SCREEN);
        } else {
            System.out.println("Login not successful");
        }

    }

    @FXML
    private void onButtonSignUpPressed() {
        setScene(buttonLogIn.getScene(), ModulFXML.REGISTER, ModulTitles.REGISTER);
    }



}
