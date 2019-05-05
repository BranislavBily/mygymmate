package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        boolean badInput = false;

        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();

        if (textFieldNameOfExcersise.getText().equals("")) {
            badInput = true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldNameOfExcersise);

        }
        if (textFieldRepetitions.getText().equals("") || !numberOrNot(textFieldRepetitions.getText())) {
            badInput = true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldRepetitions);
        }
        if (textFieldWeight.getText().equals("") || !numberOrNot(textFieldWeight.getText())) {
            badInput = true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldWeight);
        }
        if (!badInput) {
            Workout workout = loadWorkoutFromInput();
            databaseModuleWorkout.insertWorkout(workout);
            labelWorkoutAdded.setVisible(true);
        }
    }

    private Workout loadWorkoutFromInput() {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        Workout workout = new Workout();
        workout.setExercise(textFieldNameOfExcersise.getText());
        workout.setRepetitions(Integer.parseInt(textFieldRepetitions.getText()));
        workout.setWeight(textFieldWeight.getText());
        workout.setDate(date);
        return workout;
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
