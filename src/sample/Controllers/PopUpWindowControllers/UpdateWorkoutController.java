package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import sample.Controllers.FeedbackController;

public class UpdateWorkoutController extends FeedbackController {
    @FXML
    private TextField textFieldNameOfExcersise;
    @FXML
    private TextField textFieldRepetitions;
    @FXML
    private TextField textFieldWeight;
    @FXML
    private Label labelSuccess;
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

    private DatabaseModuleWorkout databaseModuleWorkout;

    private int workoutID;

    public void onCreate(int id) {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        workoutID = id;
        fillTextFields(workoutID);
    }


    private void fillTextFields(int id) {
        Workout workout = databaseModuleWorkout.getWorkout(id);
        textFieldNameOfExcersise.setText(workout.getExercise());
        textFieldRepetitions.setText(String.valueOf(workout.getRepetitions()));
        String weight = workout.getWeight();
        if (weight.equals("Bodyweight")) {
            checkBoxBodyweight.setSelected(true);
            textFieldWeight.setVisible(false);
        }
        else textFieldWeight.setText(weight);
    }

    private Workout getWorkoutFromInput() {
        Workout workout = new Workout();
        workout.setRepetitions(Integer.parseInt(textFieldRepetitions.getText()));
        workout.setExercise(textFieldNameOfExcersise.getText());
        String weight = textFieldWeight.isVisible() ? textFieldWeight.getText() : "Bodyweight";
        workout.setWeight(weight);
        workout.setId(workoutID);
        return workout;
    }

    private boolean checkInput() {
        resetAllFeedback();
        boolean goodInput = true;

        String repetitions = textFieldRepetitions.getText();
        String exercise = textFieldNameOfExcersise.getText();

        if (exercise.isEmpty()) {
            displayFeedBack(textFieldNameOfExcersise);
            labelExerciseError.setText("Please fill an exercise!");
            labelExerciseError.setVisible(true);
            goodInput = false;
        }

        if (repetitions.isEmpty() || !isInteger(repetitions) || repetitions.contains(" ")) {
            displayFeedBack(textFieldRepetitions);
            labelRepetitionsError.setText("Please set repetitions!");
            labelRepetitionsError.setVisible(true);
            goodInput = false;
        } else if (Integer.parseInt(repetitions) < 0) {
            displayFeedBack(textFieldRepetitions);
            labelRepetitionsError.setText("Please set repetitions!");
            labelRepetitionsError.setVisible(true);
        }

        if (textFieldWeight.isVisible()) {
            System.out.println("Beriem weight");
            if (!isDouble(textFieldWeight.getText()) ||
                    textFieldWeight.getText().contains(" ") ||
                    textFieldWeight.getText().isEmpty()) {
                displayFeedBack(textFieldWeight);
                labelWeightError.setText("Please insert weight!");
                labelWeightError.setVisible(true);
                goodInput = false;
            } else if (Double.parseDouble(textFieldWeight.getText()) < 1) {
                displayFeedBack(textFieldWeight);
                labelWeightError.setText("Weight needs to be 1kg and heavier!");
                labelWeightSmall.setVisible(true);
                goodInput = false;
            }
        }
        return goodInput;
    }

    @FXML
    private void onButtonSavePressed() {
        if (checkInput()) {
            Workout workout = getWorkoutFromInput();
            if (databaseModuleWorkout.updateWorkout(workout)) {
                labelSuccess.setVisible(true);
            } else {
                System.out.println("Error in inserting update");
            }
            System.out.println(workout.toString());
        }
    }

    @FXML
    private void onCheckBoxStateChange() {
        if (checkBoxBodyweight.isSelected()) {
            textFieldWeight.setVisible(false);
            labelWeightError.setVisible(false);
            labelWeightSmall.setVisible(false);
        } else {
            textFieldWeight.setVisible(true);

        }
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
    }


}
