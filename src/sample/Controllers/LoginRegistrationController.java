package sample.Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class LoginRegistrationController extends Controller {

    //Treba zmenit aj focus

    protected void displayErrorFeedbackUsername(TextField textField){
        textField.setText("");
        DropShadow dropShadowUsername = new DropShadow();
       dropShadowUsername.setRadius(20);
       dropShadowUsername.setColor(Color.color(1,0,0));
        textField.setEffect(dropShadowUsername);
    }

    protected void displayErrorFeedbackPassword(PasswordField passwordField){
        passwordField.setText("");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setColor(Color.color(1,0,0));
        passwordField.setEffect(dropShadow);
    }
    protected void displayErrorFeedbackChoiceBox(ChoiceBox choiceBox){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setColor(Color.color(1,0,0));
        choiceBox.setEffect(dropShadow);
    }



    //Reverse vsetok feedback aby to vyzeralo tak ako pred zobrazenim feedbacku
    protected void reverseFeedback() {

    }
}
