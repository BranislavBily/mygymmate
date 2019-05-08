package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import sample.Controllers.FeedbackController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWorkoutController extends FeedbackController {

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
        boolean goodInput = true;

        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();

        if (textFieldNameOfExcersise.getText().equals("")) {
            goodInput = false;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldNameOfExcersise);

        }
        if (textFieldRepetitions.getText().equals("") || !isInteger(textFieldRepetitions.getText())) {
            goodInput = false;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldRepetitions);
        }
        if (textFieldWeight.getText().equals("") || !isDouble(textFieldWeight.getText())) {
            goodInput = false;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldWeight);
        }
        if (goodInput) {
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

    private void resetAllFeedback() {
        DropShadow dropShadow = getCleanDropShadow();
        textFieldNameOfExcersise.setEffect(dropShadow);
        textFieldRepetitions.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        labelEmptyFieldError.setVisible(false);
        labelWorkoutAdded.setVisible(false);
    }
}
