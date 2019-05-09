package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleMeasurements;
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

import java.io.IOException;


public class MeasureController extends HomeSceneController {

    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonMeasurements;
    @FXML
    private Button buttonProgress;
    @FXML
    private Label labelUsername;

    private DatabaseModuleUser databaseModuleUser;
    private DatabaseModuleMeasurements databaseModuleMeasurements;

    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        setLabel();
        onButtonMeasurementsPressed();
        System.out.println(databaseModuleMeasurements.getUserMeasurement().toString());
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
        String username = databaseModuleUser.getUsername();
        setLabelUsername(labelUsername, username);
    }
    @FXML
    private void onGoBackImagePressed() {
        String status = databaseModuleUser.getUserStatus();
        if(status.equals(ResourceUserType.TRAINEE)) {
            setSceneToTraineeHomeScene(buttonMeasurements.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonMeasurements.getScene());
        }
    }
}
