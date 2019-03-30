package sample.Controllers.SceneControllers.LoginRegister;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Controllers.LoginRegistrationController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Security.PasswordChecker;
import sample.Users.Trainee.Trainee;
import sample.Users.User;

public class RegisterController extends LoginRegistrationController {

    @FXML
    private Pane pane;

    @FXML
    private Hyperlink hyperLinkAlreadyMember;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private PasswordField passwordFieldPasswordAgain;

    @FXML
    private Label labelPasswordMismatch;

    @FXML
    private Label labelUsernameTaken;

    @FXML
    private Label mandatoryError;

    @FXML
    private Label mandatoryError2;

    @FXML
    private void onButtonSignUpPressed() {
        boolean errorRegistering = false;
        resetAllFeedback();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        //There Strings can be null when user just pressed sign up
        String username = textFieldUsername.getText();
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();


        //If username is empty
        if (username == null || username.equals("")) {
            displayErrorFeedbackUsername(textFieldUsername);
            mandatoryError.setVisible(true);
            System.out.println("This field is mandatory");
            errorRegistering = true;
            //Else if username is in the database
        } else if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);
            labelUsernameTaken.setVisible(true);
            errorRegistering = true;
            System.out.println("Username taken");

        }
        //If either of them is empty
        if (password == null || passwordAgain == null || password.equals("") || passwordAgain.equals("")) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            mandatoryError2.setVisible(true);
            errorRegistering = true;
            System.out.println("Error: These fields are mandatory");
            //If passwords do not match
        } else if (!password.equals(passwordAgain)) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            labelPasswordMismatch.setVisible(true);
            errorRegistering = true;
            System.out.println("Passwords do not match");
        }
        if (!errorRegistering) {
            User user = new Trainee(username, password);
            setScene(textFieldUsername.getScene(), ModuleFXML.REGISTER_INFO, user);
        }
    }
    //In case user corrects himself, All feedback must be gone so when he makes mistake only the correct feedback will be shown
    private void resetAllFeedback() {
        labelPasswordMismatch.setVisible(false);
        labelUsernameTaken.setVisible(false);
        mandatoryError.setVisible(false);
        mandatoryError2.setVisible(false);
        DropShadow usernameShadow = (DropShadow) textFieldUsername.getEffect();
        usernameShadow.setColor(Color.BLACK);
        textFieldUsername.setEffect(usernameShadow);
        passwordFieldPassword.setEffect(usernameShadow);
        passwordFieldPasswordAgain.setEffect(usernameShadow);
    }

    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

}



