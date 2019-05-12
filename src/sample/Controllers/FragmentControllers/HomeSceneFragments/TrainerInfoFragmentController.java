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
    private Label labelWorkouts;
    @FXML
    private Label labelDiet;
    @FXML
    private Label labelMeasurements;

    private DatabaseModuleMeasurements databaseModuleMeasurements;
    private DatabaseModuleDiet databaseModuleDiet;
    private DatabaseModuleWorkout databaseModuleWorkout;
    private DatabaseModuleInfo databaseModuleInfo;

    public void onCreate() {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        databaseModuleDiet = new DatabaseModuleDiet();
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        databaseModuleInfo = new DatabaseModuleInfo();
        Integer trainerID = databaseModuleInfo.getMyTrainerID();
        if(trainerID == null) {
            labelUsername.setText("No trainer");
        } else {
            labelUsername.setText(databaseModuleInfo.getMyTrainer().getRealName());
            labelWorkouts.setText(databaseModuleWorkout.getWorkouts(trainerID).toString());
            labelWorkouts.setWrapText(true);
            labelWorkouts.setMaxWidth(500);
            labelDiet.setText(databaseModuleDiet.getUserDietInfo(trainerID).toString());
            labelDiet.setWrapText(true);
            labelDiet.setMaxWidth(500);
            labelMeasurements.setText(databaseModuleMeasurements.getUserMeasurement(trainerID).toString());
            labelMeasurements.setWrapText(true);
            labelMeasurements.setMaxWidth(500);
        }
    }
}
