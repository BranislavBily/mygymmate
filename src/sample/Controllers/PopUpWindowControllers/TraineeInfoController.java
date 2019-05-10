package sample.Controllers.PopUpWindowControllers;

import db.DatabaseModuleDiet;
import db.DatabaseModuleMeasurements;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TraineeInfoController {

    @FXML
    private Label labelWorkout;
    @FXML
    private Label labelDiet;
    @FXML
    private Label labelMeasurements;

    DatabaseModuleWorkout databaseModuleWorkout;
    DatabaseModuleDiet databaseModuleDiet;
    DatabaseModuleMeasurements databaseModuleMeasurements;

    public void onCreate(int traineeID) {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        databaseModuleDiet = new DatabaseModuleDiet();
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        labelWorkout.setText(databaseModuleWorkout.getWorkouts(traineeID).toString());
        labelWorkout.setMaxWidth(500);
        labelWorkout.setWrapText(true);
        labelDiet.setText(databaseModuleDiet.getUserDietInfo(traineeID).toString());
        labelDiet.setWrapText(true);
        labelDiet.setMaxWidth(500);
        labelMeasurements.setText(databaseModuleMeasurements.getUserMeasurement(traineeID).toString());
        labelMeasurements.setWrapText(true);
        labelMeasurements.setMaxWidth(500);
    }
}
