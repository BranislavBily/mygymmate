package sample.Controllers.FragmentControllers.WorkoutSceneFragments;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProgressWorkoutController {

    @FXML
    private ChoiceBox<String> choiceBoxExercise;
    @FXML
    private Label labelWorkouts;

    private DatabaseModuleWorkout databaseModuleWorkout;

    public void onCreate() {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        loadChoiceBox();
    }

    private void loadChoiceBox() {
        LinkedHashSet<String> exercises = databaseModuleWorkout.getAllUsersExercises();
        if(exercises.size() > 0) {
            choiceBoxExercise.setItems(FXCollections.observableArrayList(exercises));
            choiceBoxExercise.setValue(exercises.iterator().next());
            setLabelWorkoutsByExercise(exercises.iterator().next());
            choiceBoxExercise.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(choiceBoxExercise));
        }
    }

    private class MyChangeListener implements ChangeListener<String> {
        final ChoiceBox<String> cb;

        MyChangeListener(ChoiceBox<String> cb) {
            this.cb = cb;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            setLabelWorkoutsByExercise(newValue);
        }
    }

    private void setLabelWorkoutsByExercise(String exercise) {
        ArrayList<Workout> workouts = databaseModuleWorkout.getWorkoutsByExerciseName(exercise);
        if (workouts != null) labelWorkouts.setText(workouts.toString());
    }
}
