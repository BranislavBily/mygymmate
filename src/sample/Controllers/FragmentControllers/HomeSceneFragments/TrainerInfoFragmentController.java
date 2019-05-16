package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TrainerInfoFragmentController {

    @FXML
    private Label labelUsername;
    @FXML
    private Label labelWorkout;
    @FXML
    private Label labelDiet;
    @FXML
    private Label labelMeasurement;
    @FXML
    private Label labelWeight;

    public void onCreate() {
        loadTrainerInfo();
    }

    private void loadTrainerInfo() {
        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();
        DatabaseModuleDiet databaseModuleDiet = new DatabaseModuleDiet();
        DatabaseModuleMeasurements databaseModuleMeasurements = new DatabaseModuleMeasurements();
        DatabaseModuleInfo databaseModuleInfo = new DatabaseModuleInfo();
        Integer trainerID = databaseModuleInfo.getMyTrainerID();
        if (trainerID == null) {
            labelUsername.setText("No trainer");
        } else {
            labelUsername.setText(databaseModuleInfo.getMyTrainer().getRealName());
            labelWorkout.setText(databaseModuleWorkout.getWorkouts(trainerID).toString());
            labelWorkout.setWrapText(true);
            labelWorkout.setMaxWidth(500);
            labelWeight.setText(databaseModuleDiet.getUserDietInfo(trainerID).toString());
            labelWeight.setWrapText(true);
            labelWeight.setMaxWidth(500);
            if (databaseModuleMeasurements.getUserMeasurement(trainerID) != null) {
                labelMeasurement.setText(databaseModuleMeasurements.getUserMeasurement(trainerID).toString());
                labelMeasurement.setWrapText(true);
                labelMeasurement.setMaxWidth(500);
            }
        }
    }
}
