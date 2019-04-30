package sample.Controllers.SceneControllers.LoginRegister;

import db.DTO.Workout;
import db.DatabaseModuleUser;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Controllers.LoginRegistrationController;

import java.util.ArrayList;

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
            String status = databaseModuleUser.getUserStatus(userID);
            System.out.println(status);
            switch (status) {
                case "Trainee" : setSceneToTraineeHomeScene(buttonLogIn.getScene(), userID);break;
                case "Trainer" : setSceneToTrainerHomeScene(buttonLogIn.getScene(), userID);break;
                case "Admin" : setSceneToAdminHomeScene(buttonLogIn.getScene(), userID);break;
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
