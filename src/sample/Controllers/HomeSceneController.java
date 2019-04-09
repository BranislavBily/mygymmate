package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.FeatureScene.WorkoutController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;

import java.io.IOException;

//Class for methods specific for Home Scenes, including Workout Measure and Diet
public class HomeSceneController extends Controller {

    protected void setSceneToWorkout(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToWorkout(stage, user);
    }

    private void changeSceneToWorkout(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.WORKOUTS_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.WORKOUTS);
            stage.setResizable(false);
            stage.show();
            WorkoutController workoutController = loader.getController();
            workoutController.setUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onButtonLogOutPressed(Button buttonLogOut) {
        setSceneToLogin(buttonLogOut.getScene());
    }

    //Fills trainee with information from database
    protected Trainee loadTrainee(int userID) {
        return new Trainee();
    }

    //Fills trainer with information from database
    protected Trainer loadTrainer(int userID) {
        return new Trainer();
    }


}
