package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class UpdateWorkoutController {
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
        boolean badInput = false;

        String repetitions = textFieldRepetitions.getText();
        String exercise = textFieldNameOfExcersise.getText();

        if (exercise.isEmpty()) {
            displayFeedBack(textFieldNameOfExcersise);
            labelExerciseError.setText("Please fill an exercise!");
            labelExerciseError.setVisible(true);
            badInput = true;
        }

        if (repetitions.isEmpty() || !numberOrNot(repetitions) || repetitions.contains(" ")) {
            displayFeedBack(textFieldRepetitions);
            labelRepetitionsError.setText("Please set repetitions!");
            labelRepetitionsError.setVisible(true);
            badInput = true;
        }

        if (textFieldWeight.isVisible()) {
            System.out.println("Beriem weight");
            if (!numberOrNot(textFieldWeight.getText()) ||
                    textFieldWeight.getText().contains(" ") ||
                    textFieldWeight.getText().isEmpty()) {
                displayFeedBack(textFieldWeight);
                labelWeightError.setText("Please insert weight!");
                labelWeightError.setVisible(true);
                badInput = true;
            } else if (Double.parseDouble(textFieldWeight.getText()) < 1) {
                displayFeedBack(textFieldWeight);
                labelWeightSmall.setVisible(true);
                badInput = true;
            }
        }
        return !badInput;
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
        } else {
            textFieldWeight.setVisible(true);
        }
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0);
        dropShadow.setColor(Color.color(0, 0, 0));
        textFieldNameOfExcersise.setEffect(dropShadow);
        textFieldRepetitions.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        labelWeightError.setVisible(false);
        labelRepetitionsError.setVisible(false);
        labelExerciseError.setVisible(false);
        labelWeightSmall.setVisible(false);
    }

    private void displayFeedBack(TextField textField) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.color(1, 0, 0));
        textField.setEffect(dropShadow);
    }

    private boolean numberOrNot(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }


}
