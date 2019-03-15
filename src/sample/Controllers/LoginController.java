package sample.Controllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModulTitles;

public class LoginController extends Controller {

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
        if(databaseModuleUser.isUser(username, password)) {
            setScene(buttonLogIn.getScene(), ModulFXML.USER_HOME_SCREEN, ModulTitles.USER_HOME_SCREEN);
        } else {

            passwordFieldPassword.setText(null);
           DropShadow passwordShadow = (DropShadow) passwordFieldPassword.getEffect();
                passwordShadow.setColor(Color.RED);
                passwordShadow.setRadius(30);

                textFieldUserName.setText(null);
           DropShadow usernameShadow = (DropShadow) textFieldUserName.getEffect();
                usernameShadow.setColor(Color.RED);
                usernameShadow.setRadius(30);


                loginError.setVisible(true);
            System.out.println("Login not successful");
        }

    }

    @FXML
    private void onButtonSignUpPressed() {
        setScene(buttonLogIn.getScene(), ModulFXML.REGISTER, ModulTitles.REGISTER);
    }

}
