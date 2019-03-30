package sample.Controllers.SceneControllers.LoginRegister;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Controllers.LoginRegistrationController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;

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
        //Creates user based on login credentials
        User user = databaseModuleUser.isUser(username, password);
        if (user != null) {
            if(user instanceof Trainee) {
                setScene(textFieldUserName.getScene(), ModuleFXML.USER_HOME_SCREEN, ModuleTitles.USER_HOME_SCREEN);
            } else if (user instanceof Trainer) {

                setScene(textFieldUserName.getScene(), ModuleFXML.TRAINER_HOME_SCREEN, ModuleTitles.USER_HOME_SCREEN);
            } else {
                setScene(textFieldUserName.getScene(), ModuleFXML.ADMIN_HOME_SCREEN, ModuleTitles.USER_HOME_SCREEN);

            }
        //If user was not logged in
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
