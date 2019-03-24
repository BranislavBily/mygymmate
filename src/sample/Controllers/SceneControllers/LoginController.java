package sample.Controllers.SceneControllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Controllers.LoginRegistrationController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;

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

            if(databaseModuleUser.isAdmin()&& databaseModuleUser.isUser(username,password)){
                setScene(buttonLogIn.getScene(), ModuleFXML.ADMIN_HOME_SCREEN, ModuleTitles.ADMIN_HOME_SCREEN);

            }else if (databaseModuleUser.isTrainer()&& databaseModuleUser.isUser(username,password)) {
                setScene(buttonLogIn.getScene(), ModuleFXML.TRAINER_HOME_SCREEN, ModuleTitles.TRAINER_HOME_SCREEN);

            } else {
                setScene(buttonLogIn.getScene(), ModuleFXML.USER_HOME_SCREEN, ModuleTitles.USER_HOME_SCREEN);
            }
        } else {

            displayErrorFeedbackUsername(textFieldUserName);
            displayErrorFeedbackPassword(passwordFieldPassword);

            loginError.setVisible(true);

            System.out.println("Login not successful");
        }
    }

    @FXML
    private void onButtonSignUpPressed() {
        setScene(buttonLogIn.getScene(), ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }



}
