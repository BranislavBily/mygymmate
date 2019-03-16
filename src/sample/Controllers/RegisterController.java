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
import sample.Moduls.ModulTitles;
import sample.User;

import java.sql.SQLException;

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
    private Label mandatoryError;

    @FXML
    private Label mandatoryError2;

    @FXML
    private void onButtonSignUpPressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = textFieldUsername.getText();
        System.out.println(username);
        String password = passwordFieldPassword.getText();
        String passwordAgain = passwordFieldPasswordAgain.getText();

        if (databaseModuleUser.isUsernameTaken(username)) {
            displayErrorFeedbackUsername(textFieldUsername);
            if (mandatoryError2.isVisible()){
                mandatoryError2.setVisible(false);
            }
            usernameTaken.setVisible(true);
            System.out.println("Username taken");


        } else if (!password.equals(passwordAgain)) {
            displayErrorFeedbackPassword(passwordFieldPassword, passwordFieldPasswordAgain);
            if(mandatoryError.isVisible()){
                mandatoryError.setVisible(false);
            }
            passwordMismatch.setVisible(true);
            System.out.println("Passwords do not match");

        }
        else if (passwordFieldPassword.getText().equals("")&&textFieldUsername.getText().equals("")) {
            displayErrorFeedbackUsername(textFieldUsername);
            displayErrorFeedbackPassword(passwordFieldPassword, passwordFieldPasswordAgain);
            if (usernameTaken.isVisible() || passwordMismatch.isVisible()) {
                usernameTaken.setVisible(false);
                passwordMismatch.setVisible(false);
            }

            mandatoryError.setVisible(true);
            mandatoryError2.setVisible(true);
            System.out.println("These fields are mandatory");

        }else if ((!databaseModuleUser.isUsernameTaken(username)&& !password.equals(passwordAgain))||(
                !databaseModuleUser.isUsernameTaken(username)&& password.equals("")&&passwordAgain.equals(""))){
            DropShadow usernameShadow = (DropShadow) textFieldUsername.getEffect();
            if(usernameShadow.getColor().equals(Color.RED)) {
                usernameShadow.setColor(Color.BLACK);
                textFieldUsername.setEffect(usernameShadow);
                mandatoryError2.setVisible(false);
                usernameTaken.setVisible(false);
                mandatoryError.setVisible(true);


            }

        }
        else if ((databaseModuleUser.isUsernameTaken(username)&& password.equals(passwordAgain))||(
                databaseModuleUser.isUsernameTaken(username)&& !password.equals("")&&!passwordAgain.equals(""))){
            DropShadow passwordShadow = (DropShadow) passwordFieldPassword.getEffect();
            if(passwordShadow.getColor().equals(Color.RED)) {
                passwordShadow.setColor(Color.BLACK);
                passwordFieldPassword.setEffect(passwordShadow);
                passwordFieldPasswordAgain.setEffect(passwordShadow);
                mandatoryError2.setVisible(false);
                usernameTaken.setVisible(true);
                mandatoryError.setVisible(false);



            }

        }
        else if (passwordFieldPassword.getText().equals("")){
            displayErrorFeedbackPassword(passwordFieldPassword,passwordFieldPasswordAgain);
            if (usernameTaken.isVisible() || passwordMismatch.isVisible()) {
                usernameTaken.setVisible(false);
                passwordMismatch.setVisible(false);
            }
            mandatoryError.setVisible(true);
            System.out.println("This field is mandatory");

        }
        else if (textFieldUsername.getText().equals("")){
            displayErrorFeedbackUsername(textFieldUsername);
            if (usernameTaken.isVisible() || passwordMismatch.isVisible()) {
                usernameTaken.setVisible(false);
                passwordMismatch.setVisible(false);
            }
            mandatoryError2.setVisible(true);
            System.out.println("This field is mandatory");
        }


        else {
            User user = new User(username, password);
            setScene(textFieldUsername.getScene(), ModulFXML.REGISTER_INFO, user);
        }
    }


    @FXML
    private void onHyperLinkPressed() {
        setScene(hyperLinkAlreadyMember.getScene(), ModulFXML.LOGIN, ModulTitles.LOG_IN);
    }
    private void displayErrorFeedbackUsername(TextField textField){
        textField.setText("");
        DropShadow usernameShadow = (DropShadow) textField.getEffect();
        usernameShadow.setColor(Color.RED);
        usernameShadow.setRadius(30);

    }

    private void displayErrorFeedbackPassword(PasswordField passwordField, PasswordField passwordField2){
        passwordField.setText("");
        DropShadow passwordShadow = (DropShadow) passwordField.getEffect();
        passwordShadow.setColor(Color.RED);
        passwordShadow.setRadius(30);

        passwordField2.setText("");
        DropShadow passwordShadowAgain = (DropShadow) passwordField2.getEffect();
        passwordShadowAgain.setColor(Color.RED);
        passwordShadowAgain.setRadius(30);

    }







    }

