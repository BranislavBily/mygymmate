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
        resetAllFeedback();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();

        hideAllFeedback();

        if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);

            if (mandatoryError2.isVisible()) {
                mandatoryError2.setVisible(false);
            }

            labelUsernameTaken.setVisible(true);
            System.out.println("Username taken");

        } else if (!password.equals(passwordAgain)) {

            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            if (mandatoryError.isVisible()) {
                mandatoryError.setVisible(false);
            }

            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            labelPasswordMismatch.setVisible(true);

            System.out.println("Passwords do not match");

        } else if (passwordFieldPassword.getText().equals("")) {
            displayErrorFeedbackUsername(textFieldUsername);
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            if (labelUsernameTaken.isVisible() || labelPasswordMismatch.isVisible()) {
                labelUsernameTaken.setVisible(false);
                labelPasswordMismatch.setVisible(false);
            }

            mandatoryError.setVisible(true);
            mandatoryError2.setVisible(true);
            System.out.println("These fields are mandatory");

        } else if ((!databaseModuleUser.isUsernameTaken(username) && !password.equals(passwordAgain)) || (
                !databaseModuleUser.isUsernameTaken(username) && password.equals("") && passwordAgain.equals(""))) {
            DropShadow usernameShadow = (DropShadow) textFieldUsername.getEffect();
            if (usernameShadow.getColor().equals(Color.RED)) {
                usernameShadow.setColor(Color.BLACK);
                textFieldUsername.setEffect(usernameShadow);
                mandatoryError2.setVisible(false);
                labelUsernameTaken.setVisible(false);
                mandatoryError.setVisible(true);


            }

        } else if ((databaseModuleUser.isUsernameTaken(username) && password.equals(passwordAgain)) || (
                databaseModuleUser.isUsernameTaken(username) && !password.equals("") && !passwordAgain.equals(""))) {
            DropShadow passwordShadow = (DropShadow) passwordFieldPassword.getEffect();
            if (passwordShadow.getColor().equals(Color.RED)) {
                passwordShadow.setColor(Color.BLACK);
                passwordFieldPassword.setEffect(passwordShadow);
                passwordFieldPasswordAgain.setEffect(passwordShadow);
                mandatoryError2.setVisible(false);
                labelUsernameTaken.setVisible(true);
                mandatoryError.setVisible(false);


            }

        } else if (passwordFieldPassword.getText().equals("")) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            if (labelUsernameTaken.isVisible() || labelPasswordMismatch.isVisible()) {
                labelUsernameTaken.setVisible(false);
                labelPasswordMismatch.setVisible(false);
            }
            mandatoryError.setVisible(true);
            System.out.println("This field is mandatory");

        } else if (textFieldUsername.getText().equals("")) {
            displayErrorFeedbackUsername(textFieldUsername);
            if (labelUsernameTaken.isVisible() || labelPasswordMismatch.isVisible()) {
                labelUsernameTaken.setVisible(false);
                labelPasswordMismatch.setVisible(false);
            }
            mandatoryError2.setVisible(true);
            System.out.println("This field is mandatory");
        } else {
            User user = new User(username, password);
            setScene(textFieldUsername.getScene(), ModuleFXML.REGISTER_INFO, user);
        }
    }

    //TODO Password Checker

    //In case user corrects himself, All feedback must be gone so when he makes mistake only the correct feedback will be shown
    private void resetAllFeedback() {
        labelPasswordMismatch.setVisible(false);
        labelUsernameTaken.setVisible(false);
    }

    @FXML
    private void onHyperLinkPressed() {

        setScene(hyperLinkAlreadyMember.getScene(), ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

    private void hideAllFeedback() {

    }

}

