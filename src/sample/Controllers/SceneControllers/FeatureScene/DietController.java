package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceUserType;
import sample.Session;

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
            setSceneToTraineeHomeScene(buttonDiets.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonDiets.getScene());
        }
    }

}
