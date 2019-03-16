package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModuleTitles;

public class LoginController extends LoginRegistrationController {

    @FXML
    private TextField textFieldUserName;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Label loginError;

    @FXML
    private void onButtonLogInPressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUserName.getText();
        String password = passwordFieldPassword.getText();
        if (databaseModuleUser.isUser(username, password)) {
            setScene(buttonLogIn.getScene(), ModulFXML.USER_HOME_SCREEN, ModuleTitles.USER_HOME_SCREEN);
        } else {

            displayErrorFeedbackUsername(textFieldUserName);
            displayErrorFeedbackPassword(passwordFieldPassword);

            loginError.setVisible(true);

            System.out.println("Login not successful");
        }
    }

    @FXML
    private void onButtonSignUpPressed() {
        setScene(buttonLogIn.getScene(), ModulFXML.REGISTER, ModuleTitles.REGISTER);
    }



}
