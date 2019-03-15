package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModuleTitles;
import sample.User;

public class RegisterController extends Controller {

    @FXML
    private Hyperlink hyperLinkAlreadyMember;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private PasswordField passwordFieldPasswordAgain;

    @FXML
    private Label passwordMismatch;

    @FXML
    private Label usernameTaken;

    @FXML
    private void onButtonSignUpPressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();
        if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);
            usernameTaken.setVisible(true);
            System.out.println("Username taken");


        } else if (!password.equals(passwordAgain)) {
            displayErrorFeedbackPassword(passwordFieldPassword, passwordFieldPasswordAgain);
            passwordMismatch.setVisible(true);
            System.out.println("Passwords do not match");

        } else {
            User user = new User(username, password);
            setScene(textFieldUsername.getScene(), ModulFXML.REGISTER_INFO, user);
        }
    }


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModuleTitles.LOG_IN);
    }
    private void displayErrorFeedbackUsername(TextField textField){
        textField.setText(null);
        DropShadow usernameShadow = (DropShadow) textField.getEffect();
        usernameShadow.setColor(Color.RED);
        usernameShadow.setRadius(30);

    }

    private void displayErrorFeedbackPassword(PasswordField passwordField, PasswordField passwordField2){
        passwordField.setText(null);
        DropShadow passwordShadow = (DropShadow) passwordField.getEffect();
        passwordShadow.setColor(Color.RED);
        passwordShadow.setRadius(30);

        passwordField2.setText(null);
        DropShadow passwordShadowAgain = (DropShadow) passwordField2.getEffect();
        passwordShadowAgain.setColor(Color.RED);
        passwordShadowAgain.setRadius(30);

    }

}
