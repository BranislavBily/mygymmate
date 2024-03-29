package sample.Controllers.FragmentControllers.HomeSceneFragments;

import db.*;
import db.DTO.Measurement;
import db.DTO.Workout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import sample.Controllers.PopUpWindowControllers.TraineeInfoController;

import java.util.LinkedHashSet;

public class TrainerInfoFragmentController {

    @FXML
    private Label labelUsername;
    @FXML
    private Label labelWorkout;
    @FXML
    private Label labelWeight;
    @FXML
    private Label labelMeasurements;
    @FXML
    private Label labelBMI;
    @FXML
    private Label labelHeight;
    @FXML
    private ChoiceBox<String> choiceBoxExercise;


    private DatabaseModuleWorkout databaseModuleWorkout;
    private DatabaseModuleDiet databaseModuleDiet;
    private DatabaseModuleMeasurements databaseModuleMeasurements;
    private DatabaseModuleWeight databaseModuleWeight;
    private DatabaseModuleInfo databaseModuleInfo;
    private Integer trainerID;

    public void onCreate() {
        databaseModuleInfo = new DatabaseModuleInfo();
        trainerID=databaseModuleInfo.getMyTrainerID();
        if(trainerID == null) {
            System.out.println("No trainer");
            labelUsername.setText("No trainer");
        } else {
            System.out.println("Trainer");
            System.out.println(trainerID);
            loadLabels();
            loadChoiceBox();
        }
    }

    public double getBMI(double weight, double height) {

        return Math.round(weight / ((height / 100) * (height / 100)) * 10.0) / 10.0;
    }


    private void loadChoiceBox() {
        LinkedHashSet<String> exercises = databaseModuleWorkout.getAllUserExercises(trainerID);
        if (exercises.size() > 0) {
            choiceBoxExercise.setItems(FXCollections.observableArrayList(exercises));
            choiceBoxExercise.getSelectionModel().selectedItemProperty().addListener(new TrainerInfoFragmentController.MyChoiceBoxListener(choiceBoxExercise));
        } else {
            choiceBoxExercise.setItems(FXCollections.singletonObservableList("No exercises"));
            System.out.println("No exercises");
        }
    }

    private class MyChoiceBoxListener implements ChangeListener<String> {
        final ChoiceBox<String> cb;

        MyChoiceBoxListener(ChoiceBox<String> cb) {
            this.cb = cb;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            setRepetitionsByExcersise(newValue);
        }
    }


    private void loadLabels(){

        databaseModuleWorkout = new DatabaseModuleWorkout();
        databaseModuleDiet = new DatabaseModuleDiet();
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        databaseModuleWeight = new DatabaseModuleWeight();
        databaseModuleInfo=new DatabaseModuleInfo();

        labelUsername.setText(databaseModuleInfo.getMyTrainer().getRealName());
        labelUsername.setMaxWidth(500);
        labelUsername.setWrapText(true);

        labelWorkout.setText("Exercise max repetitions : ");
        labelWorkout.setMaxWidth(500);
        labelWorkout.setWrapText(true);

        labelHeight.setText("Height: "+databaseModuleDiet.getUserDietInfo(trainerID).getHeight());
        labelHeight.setWrapText(true);
        labelHeight.setMaxWidth(500);

        labelBMI.setText("BMI: " + getBMI(databaseModuleWeight.getUserWeight(trainerID).getWeight(), databaseModuleDiet.getUserDietInfo(trainerID).getHeight()));
        labelBMI.setWrapText(true);
        labelBMI.setMaxWidth(500);

        labelWeight.setText("Weight: "+databaseModuleWeight.getUserWeight(trainerID));
        labelWeight.setWrapText(true);
        labelWeight.setMaxWidth(500);

        Measurement measurement = databaseModuleMeasurements.getUserMeasurement(trainerID);
        if (measurement != null) {
            labelMeasurements.setText(measurement.toString());
            labelMeasurements.setWrapText(true);
            labelMeasurements.setMaxWidth(500);
        }
    }

    private void setRepetitionsByExcersise(String excersise){
        Workout workout=databaseModuleWorkout.getMaxRepetitionsByExercise(excersise,trainerID);
        System.out.println(workout.toString());
        String excer=excersise;
        labelWorkout.setText( excer.toUpperCase()+" max repetitions : "+workout.getRepetitions()+  "      Last Updated : "+workout.getDate());

    }
}
