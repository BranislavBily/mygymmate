package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.MeasureSceneFragments.MeasurementsFragmentController;
import sample.Controllers.FragmentControllers.MeasureSceneFragments.ProgressMeasureFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceUserType;
import sample.Session;

import java.io.IOException;


public class MeasureController extends HomeSceneController {

    private int userID;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonMeasurements;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

    public void onCreate() {
        userID = Session.getUserID();
        setLabel();
        onButtonMeasurementsPressed();
    }

    @FXML
    private void onButtonMeasurementsPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.MEASUREMENTS_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            MeasurementsFragmentController measurementsFragmentController = fxmlLoader.getController();
            measurementsFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonMeasurements ,buttonProgress);
            buttonMeasurements.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonProgressPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.PROGRESS_MEASURE_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            ProgressMeasureFragmentController progressMeasureFragmentController = fxmlLoader.getController();
            progressMeasureFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonMeasurements ,buttonProgress);
            buttonProgress.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            setSceneToTraineeHomeScene(buttonMeasurements.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonMeasurements.getScene());
        }
    }
}
