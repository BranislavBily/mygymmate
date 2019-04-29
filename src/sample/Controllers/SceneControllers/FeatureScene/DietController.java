package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
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

    private int userID;

    @FXML
    private Button buttonDiets;
    @FXML
    private Button buttonWieight;
    @FXML
    private Button buttonBMI;
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
            setSceneToTraineeHomeScene(buttonDiets.getScene(), userID);
        } else {
            setSceneToTrainerHomeScene(buttonDiets.getScene(), userID);
        }
    }

}
