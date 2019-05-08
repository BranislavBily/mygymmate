package sample.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Controller with methods for user feedback
 */
public class FeedbackController {

    protected void removeButtonActiveEffect(Button buttonHome, Button buttonProfile, Button buttonSettings, Button buttonTrainerInfo, Button buttonAboutUs){
        buttonHome.getStyleClass().remove("buttonActive");
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonTrainerInfo.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");
    }
    protected void removeAdminButtonActiveEffect(Button buttonHome ,Button buttonProfile, Button buttonSettings, Button buttonAboutUs){
        buttonHome.getStyleClass().remove("buttonActive");
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");
    }

    protected void removeButtonActiveEffect(Button button1 ,Button button2, Button button3){
        button1.getStyleClass().remove("buttonActive");
        button2.getStyleClass().remove("buttonActive");
        button3.getStyleClass().remove("buttonActive");
    }
    protected void removeButtonActiveEffect(Button button1 ,Button button2){
        button1.getStyleClass().remove("buttonActive");
        button2.getStyleClass().remove("buttonActive");
    }

    protected void displayFeedBack(TextField textField) {
        textField.setText("");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setColor(Color.color(1, 0, 0));
        textField.setEffect(dropShadow);
    }

    protected void displayFeedBack(PasswordField passwordField) {
        passwordField.setText("");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setColor(Color.color(1, 0, 0));
        passwordField.setEffect(dropShadow);
    }

    protected void displayErrorFeedbackChoiceBox(ChoiceBox choiceBox){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setColor(Color.color(1,0,0));
        choiceBox.setEffect(dropShadow);
    }

    protected boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    protected boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    protected DropShadow getCleanDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0);
        dropShadow.setColor(Color.color(0, 0, 0));
        return dropShadow;
    }


}
