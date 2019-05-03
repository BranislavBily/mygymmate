package sample.Controllers.PopUpWindowControllers;

import com.sun.org.apache.bcel.internal.generic.LADD;
import db.DatabaseModuleUser;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import javax.xml.soap.Text;

public class AddWorkoutController {

    @FXML
    private TextField textFieldNameOfExcersise;
    @FXML
    private TextField textFieldRepetitions;
    @FXML
    private TextField textFieldWeight;
    @FXML
    private Label labelEmptyFieldError;
    @FXML
    private Label labelWorkoutAdded;


    @FXML
    private void onButtonSavePressed() {
        resetAllFeedback();
        String excersise = textFieldNameOfExcersise.getText();
        int repetitions= Integer.parseInt(textFieldRepetitions.getText());
        int weight = Integer.parseInt(textFieldWeight.getText());
        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();
        databaseModuleWorkout.insertWorkout(excersise,repetitions,weight,"3.5.2019");



    }


    private boolean numberOrNot(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    private void displayFeedBack(TextField textField) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.color(1, 0, 0));
        textField.setEffect(dropShadow);
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0);
        dropShadow.setColor(Color.color(0, 0, 0));
       textFieldNameOfExcersise.setEffect(dropShadow);
       textFieldRepetitions.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        labelEmptyFieldError.setVisible(false);
        labelWorkoutAdded.setVisible(false);

    }
}
