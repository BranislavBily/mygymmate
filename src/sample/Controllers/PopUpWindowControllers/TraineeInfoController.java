package sample.Controllers.PopUpWindowControllers;

import db.*;
import db.DTO.Measurement;
import db.DTO.Workout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.FragmentControllers.WorkoutSceneFragments.ProgressWorkoutController;
import sample.Controllers.SceneControllers.Controller;
import sample.Controllers.SceneControllers.LoginRegister.LoginController;
import sample.Resources.ResourceFXML;

import java.io.IOException;
import java.util.LinkedHashSet;

public class TraineeInfoController extends DatabaseModuleUser{

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

    private int traineeID;
    private String traineeEmail;



    public void onCreate(int traineeID) {
        this.traineeID=traineeID;
        loadLabels();
        loadChoiceBox();


    }

    public double getBMI(double weight, double height) {

        return Math.round(weight / ((height / 100) * (height / 100)) * 10.0) / 10.0;
    }


    private void loadChoiceBox() {
        LinkedHashSet<String> exercises = databaseModuleWorkout.getAllUserExercises(traineeID);
        if (exercises.size() > 0) {
            choiceBoxExercise.setItems(FXCollections.observableArrayList(exercises));
            choiceBoxExercise.getSelectionModel().selectedItemProperty().addListener(new TraineeInfoController.MyChoiceBoxListener(choiceBoxExercise));
        } else {
            choiceBoxExercise.setItems(FXCollections.singletonObservableList("No exercises"));
            System.out.println("No exercises");
        }
    }

    public void onButtonSendMotivationEmailPressed() {
        Stage stage =  new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.SEND_MOTIVATION));
        try {
            Parent root = loader.load();
            TraineeInfoController traineeInfoController = loader.getController();
            traineeInfoController.onCreate(traineeID);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Send Motivation email");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void addingNotification(String email) {

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

        labelWorkout.setText("Exercise max repetitions : ");
        labelWorkout.setMaxWidth(500);
        labelWorkout.setWrapText(true);

        labelHeight.setText("Height: "+databaseModuleDiet.getUserDietInfo(traineeID).getHeight());
        labelHeight.setWrapText(true);
        labelHeight.setMaxWidth(500);

        labelBMI.setText("BMI: " + getBMI(databaseModuleWeight.getUserWeight(traineeID).getWeight(), databaseModuleDiet.getUserDietInfo(traineeID).getHeight()));
        labelBMI.setWrapText(true);
        labelBMI.setMaxWidth(500);

        labelWeight.setText("Weight: "+databaseModuleWeight.getUserWeight(traineeID));
        labelWeight.setWrapText(true);
        labelWeight.setMaxWidth(500);

        Measurement measurement = databaseModuleMeasurements.getUserMeasurement(traineeID);
        if (measurement != null) {
            labelMeasurements.setText(measurement.toString());
            labelMeasurements.setWrapText(true);
            labelMeasurements.setMaxWidth(500);
        }
    }

    private void setRepetitionsByExcersise(String excersise){
        Workout workout=databaseModuleWorkout.getMaxRepetitionsByExercise(excersise,traineeID);
        System.out.println(workout.toString());
        String excer=excersise;
        labelWorkout.setText( excer.toUpperCase()+" max repetitions : "+workout.getRepetitions()+  "        Last Updated : "+workout.getDate());

    }


}
