package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModuleTitles;
import sample.User;

public class RegisterController extends FeedBackController {

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
    private void onButtonSignUpPressed() {
        resetAllFeedback();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();
        if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);
            labelUsernameTaken.setVisible(true);
            System.out.println("Username taken");

        } else if (!password.equals(passwordAgain)) {
            displayErrorFeedbackPassword(passwordFieldPassword);
            displayErrorFeedbackPassword(passwordFieldPasswordAgain);
            labelPasswordMismatch.setVisible(true);
            System.out.println("Passwords do not match");

        } else {
            User user = new User(username, password);
            setScene(textFieldUsername.getScene(), ModulFXML.REGISTER_INFO, user);
        }
    }

    //In case user corrects himself, All feedback must be gone so when he makes mistake only the correct feedback will be shown
    private void resetAllFeedback() {
        labelPasswordMismatch.setVisible(false);
        labelUsernameTaken.setVisible(false);
    }
    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModuleTitles.LOG_IN);
    }

}
