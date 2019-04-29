package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.FeatureScene.DietController;
import sample.Controllers.SceneControllers.FeatureScene.MeasureController;
import sample.Controllers.SceneControllers.FeatureScene.WorkoutController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;

import java.io.IOException;

//Class for methods specific for Home Scenes, including Workout Measure and Diet
public class HomeSceneController extends Controller {

    protected void setSceneToWorkout(Scene scene, int userID) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToWorkout(stage, userID);
    }

    protected void setSceneToDiet(Scene scene, int userID) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToDiet(stage, userID);
    }

    protected void setSceneToMeasure(Scene scene, int userID) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToMeasureScene(stage, userID);
    }

    private void changeSceneToWorkout(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.WORKOUTS_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.WORKOUTS);
            stage.setResizable(false);
            stage.show();
            WorkoutController workoutController = loader.getController();
            workoutController.setUserID(userID);
            workoutController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToMeasureScene(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.MEASURE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.MEASURE);
            stage.setResizable(false);
            stage.show();
            MeasureController measureController = loader.getController();
            measureController.setUserID(userID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToDiet(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.DIET_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.DIET);
            stage.setResizable(false);
            stage.show();
            DietController dietController = loader.getController();
            dietController.setUserID(userID);
            dietController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onButtonLogOutPressed(Button buttonLogOut) {
        setSceneToLogin(buttonLogOut.getScene());
    }

    protected Parent loadFragmentFromFXML(String FXML){
        Parent fragment = null;
        try {
            fragment = FXMLLoader.load(getClass().getResource(FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fragment;
    }
}
