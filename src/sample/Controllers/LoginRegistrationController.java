package sample.Controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

class LoginRegistrationController extends Controller {

    //Treba zmenit aj focus

    void displayErrorFeedbackUsername(TextField textField){
        textField.setText(null);
        DropShadow usernameShadow = (DropShadow) textField.getEffect();
        usernameShadow.setColor(Color.RED);
        usernameShadow.setRadius(30);

    }

    void displayErrorFeedbackPassword(PasswordField passwordField){
        passwordField.setText(null);
        DropShadow passwordShadow = (DropShadow) passwordField.getEffect();
        passwordShadow.setColor(Color.RED);
        passwordShadow.setRadius(30);
    }

    //Reverse vsetok feedback aby to vyzeralo tak ako pred zobrazenim feedbacku
    void reverseFeedback() {

    }
}