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

public class DietController extends HomeSceneController {

    private User user;
    private Trainee trainee;
    private Trainer trainer;
    private boolean isTraineeDiet;

    @FXML
    private Button buttonDiets;
    @FXML
    private Button buttonWieight;
    @FXML
    private Button buttonBMI;
    @FXML
    private Label labelUsername;


    @FXML
    private void onGoBackImagePressed() {
        if(isTraineeDiet) {
            setSceneToTraineeHomeScene(buttonBMI.getScene(), user);
        } else {
            setSceneToTrainerHomeScene(buttonBMI.getScene(), user);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if(user instanceof Trainee) {
            isTraineeDiet = true;
            // while loadTrainee does not load from db
            //trainee = loadTrainee(user.getId());
        } else {
            isTraineeDiet = false;
            //while loadTrainer does not load from db
            //trainer = loadTrainer(user.getId());
        }
    }

}
