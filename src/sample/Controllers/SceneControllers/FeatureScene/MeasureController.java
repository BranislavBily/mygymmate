package sample.Controllers.SceneControllers.FeatureScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;


public class MeasureController extends HomeSceneController {

    private User user;
    private Trainee trainee;
    private Trainer trainer;
    private boolean isTraineeMeasure;



    @FXML
    private Button buttonMeasures;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label usernameLabel;

    public void setUser(User user) {
        this.user = user;
        if(user instanceof Trainee) {
            isTraineeMeasure = true;
            // while loadTrainee does not load from db
            //trainee = loadTrainee(user.getId());
        } else {
            isTraineeMeasure = false;
            //while loadTrainer does not load from db
            //trainer = loadTrainer(user.getId());
        }
    }

    @FXML
    private void onGoBackImagePressed() {
        if(isTraineeMeasure) {
            setSceneToTraineeHomeScene(buttonMeasures.getScene(), user);
        } else {
            setSceneToTrainerHomeScene(buttonMeasures.getScene(), user);
        }
    }
}
