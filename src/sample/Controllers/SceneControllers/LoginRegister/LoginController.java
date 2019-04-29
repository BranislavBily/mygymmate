package sample.Controllers.SceneControllers.LoginRegister;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Controllers.LoginRegistrationController;
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
        Integer userID = databaseModuleUser.isUser(username, password);
        if (userID != null) {
            String status = databaseModuleUser.getUserStatus(userID);
            switch (status) {
                case "trainee" : setSceneToTraineeHomeScene(buttonLogIn.getScene(), userID);break;
                case "trainer" : setSceneToTrainerHomeScene(buttonLogIn.getScene(), userID);break;
                case "admin" : setSceneToAdminHomeScene(buttonLogIn.getScene(), userID);break;
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
