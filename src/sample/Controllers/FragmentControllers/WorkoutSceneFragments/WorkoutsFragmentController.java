package sample.Controllers.FragmentControllers.WorkoutSceneFragments;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Session;

import java.util.ArrayList;

public class WorkoutsFragmentController {

    @FXML
    private TableView<Workout> tableViewWorkouts;
    @FXML
    private TableColumn<Workout, String> tableColumnExercise;
    @FXML
    private TableColumn<Workout, String> tableColumnRepetitions;
    @FXML
    private TableColumn<Workout, String> tableColumnWeight;
    @FXML
    private TableColumn<Workout, String> tableColumnDate;

    public void onCreate() {
        loadWorkoutsIntoTable();
    }


    private void loadWorkoutsIntoTable() {
        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();
        ArrayList<Workout> workouts = databaseModuleWorkout.loadWorkouts(Session.getUserID());
        tableColumnExercise.setCellValueFactory(new PropertyValueFactory("Exercise"));
        tableColumnRepetitions.setCellValueFactory(new PropertyValueFactory("Repetitions"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory("Weight"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory("Date"));
        ObservableList<Workout> workoutObservableList = FXCollections.observableList(workouts);
        tableViewWorkouts.setItems(workoutObservableList);
    }
}
