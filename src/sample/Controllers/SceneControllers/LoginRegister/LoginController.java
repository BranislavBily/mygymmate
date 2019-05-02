package sample.Controllers.SceneControllers.LoginRegister;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Controllers.LoginRegistrationController;
import sample.Session;

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
        Integer userID = databaseModuleUser.isUser(username, password);
        if (userID != null) {
            Session.setUserID(userID);
            String status = databaseModuleUser.getUserStatus(userID);
            switch (status) {
                case "Trainee" : setSceneToTraineeHomeScene(buttonLogIn.getScene());break;
                case "Trainer" : setSceneToTrainerHomeScene(buttonLogIn.getScene());break;
                case "Admin" : setSceneToAdminHomeScene(buttonLogIn.getScene());break;
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
        setSceneToRegister(buttonLogIn.getScene());
    }
}
