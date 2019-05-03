package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceUserType;
import sample.Session;


public class MeasureController extends HomeSceneController {

    private int userID;

    @FXML
    private Button buttonMeasures;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

    public void onCreate() {
        userID = Session.getUserID();
        setLabel();
    }

    private void setLabel() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        setLabelUsername(labelUsername, username);
    }
    @FXML
    private void onGoBackImagePressed() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String status = databaseModuleUser.getUserStatus(userID);
        if(status.equals(ResourceUserType.TRAINEE)) {
            setSceneToTraineeHomeScene(buttonMeasures.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonMeasures.getScene());
        }
    }
}
