package sample.Controllers.PopUpWindowControllers;

import db.DTO.Measurement;
import db.DatabaseModuleDiet;
import db.DatabaseModuleMeasurements;
import db.DatabaseModuleWorkout;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TraineeInfoController {

    @FXML
    private Label labelWorkout;
    @FXML
    private Label labelWeight;
    @FXML
    private Label labelMeasurements;
    @FXML
    private Label labelBMI;

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
        labelWeight.setText(databaseModuleDiet.getUserDietInfo(traineeID).toString());
        labelWeight.setWrapText(true);
        labelWeight.setMaxWidth(500);
        Measurement measurement = databaseModuleMeasurements.getUserMeasurement(traineeID);
        if (measurement != null) {
            labelMeasurements.setText(measurement.toString());
            labelMeasurements.setWrapText(true);
            labelMeasurements.setMaxWidth(500);
        }

    }
}
