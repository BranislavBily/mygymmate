package sample.Controllers.PopUpWindowControllers;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateWorkoutController {
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
    private Label labelSuccess;

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
        if(weight.equals("Bodyweight")) textFieldWeight.setText("0.0");
        else textFieldWeight.setText(weight);
    }

    private Workout getWorkoutFromInput() {
        Workout workout = new Workout();
        workout.setRepetitions(Integer.parseInt(textFieldRepetitions.getText()));
        workout.setExercise(textFieldNameOfExcersise.getText());
        workout.setWeight(textFieldWeight.getText());
        workout.setId(workoutID);
        return workout;
    }

    @FXML
    private void onButtonSavePressed() {
        Workout workout = getWorkoutFromInput();
        if(databaseModuleWorkout.updateWorkout(workout)) {
            labelSuccess.setVisible(true);
        } else {
            System.out.println("Error in inserting update");
        }
        System.out.println(workout.toString());
    }


}
