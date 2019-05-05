package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.WorkoutSceneFragments.ProgressWorkoutController;
import sample.Controllers.FragmentControllers.WorkoutSceneFragments.WorkoutsFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceUserType;

import java.io.IOException;

public class WorkoutController extends HomeSceneController {

    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonWorkouts;
    @FXML
    private Button buttonAddWorkout;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

    private DatabaseModuleUser databaseModuleUser;

    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        setLabel();
        onButtonWorkoutsPressed();
    }

    private void setLabel() {
        String username = databaseModuleUser.getUsername();
        setLabelUsername(labelUsername, username);
    }

    @FXML
    private void onButtonWorkoutsPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.WORKOUTS_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            WorkoutsFragmentController workoutsFragmentController = fxmlLoader.getController();
            workoutsFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonProgress, buttonWorkouts);
            buttonWorkouts.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void onButtonProgressPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.PROGRESS_WORKOUT_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            ProgressWorkoutController addWorkoutFragmentController = fxmlLoader.getController();
            addWorkoutFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonProgress, buttonWorkouts);
            buttonProgress.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onGoBackImagePressed() {
        String status = databaseModuleUser.getUserStatus();
        if(status.equals(ResourceUserType.TRAINEE)) {
            setSceneToTraineeHomeScene(buttonWorkouts.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonWorkouts.getScene());
        }
    }
}
