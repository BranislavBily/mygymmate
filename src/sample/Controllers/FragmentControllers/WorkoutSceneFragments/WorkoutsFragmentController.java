package sample.Controllers.FragmentControllers.WorkoutSceneFragments;

import db.DTO.Workout;
import db.DatabaseModuleWorkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.PopUpWindowControllers.UpdateWorkoutController;
import sample.Dialogs.DeleteWorkoutDialog;
import sample.Resources.ResourceFXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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

    private DatabaseModuleWorkout databaseModuleWorkout;

    public void onCreate() {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        loadWorkoutsIntoTable();
    }

    /**
     * Creates new AddWorkout scene
     */
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
            stage.showAndWait();
            loadWorkoutsIntoTable();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads workouts into the table
     */
    private void loadWorkoutsIntoTable() {
        ArrayList<Workout> workouts = databaseModuleWorkout.getWorkouts();
        tableColumnExercise.setCellValueFactory(new PropertyValueFactory("Exercise"));
        tableColumnRepetitions.setCellValueFactory(new PropertyValueFactory("Repetitions"));
        tableColumnWeight.setCellValueFactory(new PropertyValueFactory("Weight"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory("Date"));
        ObservableList<Workout> workoutObservableList = FXCollections.observableList(workouts);
        tableViewWorkouts.setItems(workoutObservableList);
        tableViewWorkouts.setPlaceholder(new Label("No workouts found!"));
    }

    @FXML
    private void onMenuItemUpdatePressed() {
        Workout workout = tableViewWorkouts.getSelectionModel().getSelectedItem();
        System.out.println(workout.toString());
        if(workout == null) {
            System.out.println("Nekliklo na nic");
        } else {
            openUpdateWorkoutScene(workout.getId());
        }
        System.out.println("update");
    }
    private void openUpdateWorkoutScene(int id) {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.UPDATE_WORKOUT));
        try {
            Parent root = loader.load();
            UpdateWorkoutController updateWorkoutController = loader.getController();
            updateWorkoutController.onCreate(id);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add Workout");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadWorkoutsIntoTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMenuItemDeletePressed() {
        Workout workout = tableViewWorkouts.getSelectionModel().getSelectedItem();
        System.out.println(workout.toString());
        if(workout == null) {
            System.out.println("Nekliklo na nic");
        } else {
            boolean dialogAnswer = getDialogAnswer();
            if(dialogAnswer) {
                if(databaseModuleWorkout.deleteWorkout(workout.getId())) {
                    System.out.println("Uspesne vymazanie");
                    loadWorkoutsIntoTable();
                } else {
                    System.out.println("Nastal error");
                }
            } else {
                System.out.println("Vypol sa dialog");
            }
        }
    }

    private boolean getDialogAnswer() {
        DeleteWorkoutDialog deleteWorkoutDialog = new DeleteWorkoutDialog(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = deleteWorkoutDialog.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
