package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.HomeSceneController;

public class WorkoutController extends HomeSceneController {
    private int userID;

    @FXML
    private Button buttonWorkouts;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

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
        if(status.equals("trainee")) {
            setSceneToTraineeHomeScene(buttonWorkouts.getScene(), userID);
        } else {
            setSceneToTrainerHomeScene(buttonWorkouts.getScene(), userID);
        }
    }
}
