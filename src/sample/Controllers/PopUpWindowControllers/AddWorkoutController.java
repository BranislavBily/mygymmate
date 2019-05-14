package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
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
    private Label labelExerciseError;
    @FXML
    private Label labelRepetitionsError;
    @FXML
    private Label labelWeightError;
    @FXML
    private CheckBox checkBoxBodyweight;
    @FXML
    private Label labelWeightSmall;
    @FXML
    private Label labelAlreadyWorkout;


    @FXML
    private void onButtonSavePressed() {
        resetAllFeedback();
        boolean goodInput = true;

        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();

        if (textFieldNameOfExcersise.getText().equals("")) {
            goodInput = false;
            labelExerciseError.setText("Please fill an exercise!");
            labelExerciseError.setVisible(true);
            displayFeedBack(textFieldNameOfExcersise);

        }
        if (textFieldRepetitions.getText().equals("") || !isInteger(textFieldRepetitions.getText())) {
            goodInput = false;
            labelRepetitionsError.setText("Please set repetitions!");
            labelRepetitionsError.setVisible(true);
            displayFeedBack(textFieldRepetitions);
        } else if (Integer.parseInt(textFieldRepetitions.getText()) < 0) {
            displayFeedBack(textFieldRepetitions);
            labelRepetitionsError.setText("Please set repetitions!");
            labelRepetitionsError.setVisible(true);
        }

        if(textFieldWeight.isVisible()) {
            if (textFieldWeight.getText().isEmpty() || !isDouble(textFieldWeight.getText())) {
                goodInput = false;
                displayFeedBack(textFieldWeight);
                labelWeightError.setText("Please insert weight!");
                labelWeightError.setVisible(true);
            } else if (Double.parseDouble(textFieldWeight.getText()) < 1) {
                displayFeedBack(textFieldWeight);
                labelWeightSmall.setText("Weight needs to be 1kg and heavier!");
                labelWeightSmall.setVisible(true);
                goodInput = false;
            }
        }

        if (goodInput) {
            Workout workout = loadWorkoutFromInput();
            if(databaseModuleWorkout.workoutAddedToday(workout)) {
                System.out.println("Dnes bolo pridanie take");
                labelAlreadyWorkout.setVisible(true);
            } else {
                databaseModuleWorkout.insertWorkout(workout);
                Stage stage = (Stage) labelWeightError.getScene().getWindow();
                stage.close();
            }
        }
    }

    private Workout loadWorkoutFromInput() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Workout workout = new Workout();
        workout.setExercise(textFieldNameOfExcersise.getText());
        workout.setRepetitions(Integer.parseInt(textFieldRepetitions.getText()));
        String weight = textFieldWeight.isVisible() ? textFieldWeight.getText() : "Bodyweight";
        workout.setWeight(weight);
        workout.setDate(date);
        return workout;
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = getCleanDropShadow();
        textFieldNameOfExcersise.setEffect(dropShadow);
        textFieldRepetitions.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        labelWeightError.setVisible(false);
        labelRepetitionsError.setVisible(false);
        labelExerciseError.setVisible(false);
        labelWeightSmall.setVisible(false);
        labelAlreadyWorkout.setVisible(false);
    }

    @FXML
    private void onCheckBoxStateChange() {
        if (checkBoxBodyweight.isSelected()) {
            textFieldWeight.setVisible(false);
        } else {
            textFieldWeight.setVisible(true);
        }
    }
}
