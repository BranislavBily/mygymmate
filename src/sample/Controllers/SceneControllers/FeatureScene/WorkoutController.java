package sample.Controllers.SceneControllers.FeatureScene;

import db.DTO.Workout;
import db.DatabaseModuleUser;
import db.DatabaseModuleWorkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Controllers.HomeSceneController;

import java.util.ArrayList;

public class WorkoutController extends HomeSceneController {
    private int userID;
    @FXML
    private Button buttonWorkouts;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;
    @FXML
    private TableView<Workout> tableViewWorkouts;
    @FXML
    private TableColumn<Workout, String> tableColumnExercise;
    @FXML
    private TableColumn<Workout, String> tableColumnRepetitions;
    @FXML
    private TableColumn<Workout, Double> tableColumnWeight;
    @FXML
    private TableColumn<Workout, String> tableColumnDate;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLabel() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        setLabelUsername(labelUsername, username);
    }

    @FXML
    private void onGoBackImagePressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String status = databaseModuleUser.getUserStatus(userID);
        if(status.equals("Trainee")) {
            setSceneToTraineeHomeScene(buttonWorkouts.getScene(), userID);
        } else {
            setSceneToTrainerHomeScene(buttonWorkouts.getScene(), userID);
        }
    }

    public void loadWorkoutsIntoTable() {
        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();
        ArrayList<Workout> workouts = databaseModuleWorkout.loadWorkouts(userID);
        tableColumnExercise.setCellValueFactory(new PropertyValueFactory("Exercise"));
        tableColumnRepetitions.setCellValueFactory(new PropertyValueFactory("Repetitions"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory("Weight"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory("Date"));
        ObservableList<Workout> workoutObservableList = FXCollections.observableList(workouts);
        tableViewWorkouts.setItems(workoutObservableList);
    }
}
