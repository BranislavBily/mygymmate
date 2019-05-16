package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.DatabaseModuleDiet;
import db.DatabaseModuleInfo;
import db.DatabaseModuleMeasurements;
import db.DatabaseModuleWorkout;
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
            labelDiet.setText(databaseModuleDiet.getUserDietInfo(trainerID).toString());
            labelDiet.setWrapText(true);
            labelDiet.setMaxWidth(500);
            if (databaseModuleMeasurements.getUserMeasurement(trainerID) != null) {
                labelMeasurement.setText(databaseModuleMeasurements.getUserMeasurement(trainerID).toString());
                labelMeasurement.setWrapText(true);
                labelMeasurement.setMaxWidth(500);
            }
        }
    }
}
