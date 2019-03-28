package sample.Controllers.SceneControllers;

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
import sample.User;

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
        PasswordChecker passwordChecker = new PasswordChecker();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();

        resetAllFeedback();

        //If username is empty
        if (textFieldUsername.getText().equals("")) {
            displayErrorFeedbackUsername(textFieldUsername);
            mandatoryError.setVisible(true);
            System.out.println("This field is mandatory");
            errorRegistering = true;
            //Else if username is in the databse
        } else if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);
            labelUsernameTaken.setVisible(true);
            errorRegistering = true;
            System.out.println("Username taken");

        }
        //If password do not match
        if (!passwordChecker.passwordEquaility(password, passwordAgain)) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            labelPasswordMismatch.setVisible(true);
            errorRegistering = true;
            System.out.println("Passwords do not match");
            //If one of them is empty, both are empty
        } else if (passwordFieldPassword.getText().equals("")) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);

            mandatoryError.setVisible(true);
            mandatoryError2.setVisible(true);
            errorRegistering = true;
            System.out.println("Error: These fields are mandatory");
        } else if(passwordChecker.checkPasswordStrength()) {

        }
        if (!errorRegistering) {
            User user = new User(username, password);
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

