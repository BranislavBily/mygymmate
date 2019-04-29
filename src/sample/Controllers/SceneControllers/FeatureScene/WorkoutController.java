package sample.Controllers.SceneControllers.FeatureScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;

import javax.swing.text.html.ImageView;

public class WorkoutController extends HomeSceneController {

    private User user;
    private Trainee trainee;
    private Trainer trainer;
    private boolean isTraineeWorkout;


    @FXML
    private Button buttonWorkouts;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

    public void setUser(User user) {
        this.user = user;
        if(user instanceof Trainee) {
            isTraineeWorkout = true;
            trainee = loadTrainee(user.getId());
        } else {
            isTraineeWorkout = false;
            trainer = loadTrainer(user.getId());
        }
        System.out.println(user.getUsername());
    }

    @FXML
    private void onGoBackImagePressed() {
        if(isTraineeWorkout) {
            setSceneToTraineeHomeScene(buttonWorkouts.getScene(), user);
        } else {
            setSceneToTrainerHomeScene(buttonProgress.getScene(), user);
        }
    }

}
