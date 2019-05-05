package sample.Controllers.FragmentControllers.WorkoutSceneFragments;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Resources.ResourceFXML;

import java.io.IOException;
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
    @FXML
    private Button buttonAddWorkout;

    private DatabaseModuleWorkout databaseModuleWorkout;

    public void onCreate() {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        loadWorkoutsIntoTable();
    }

    @FXML
    private void onButtonAddWorkout() {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.ADD_WORKOUT));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add Workout");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadWorkoutsIntoTable() {
        ArrayList<Workout> workouts = databaseModuleWorkout.loadWorkouts();
        tableColumnExercise.setCellValueFactory(new PropertyValueFactory("Exercise"));
        tableColumnRepetitions.setCellValueFactory(new PropertyValueFactory("Repetitions"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory("Weight"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory("Date"));
        ObservableList<Workout> workoutObservableList = FXCollections.observableList(workouts);
        tableViewWorkouts.setItems(workoutObservableList);
    }
}
