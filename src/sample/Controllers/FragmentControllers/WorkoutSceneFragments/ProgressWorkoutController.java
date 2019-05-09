package sample.Controllers.FragmentControllers.WorkoutSceneFragments;

import db.DatabaseModuleWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import sample.Interfaces.Progress;

import java.util.LinkedHashSet;
import java.util.Map;

public class ProgressWorkoutController implements Progress {

    @FXML
    private ChoiceBox<String> choiceBoxExercise;
    @FXML
    private LineChart<String, Integer> lineChartRepetitions;

    @FXML
    private LineChart<String, Double> lineChartWeight;

    private DatabaseModuleWorkout databaseModuleWorkout;


    /**
     * Prepared scene for use
     */
    public void onCreate() {
        databaseModuleWorkout = new DatabaseModuleWorkout();
        loadChoiceBox();
    }


    /**
     * Loads all exercises into the ChoiceBox
     */
    @Override
    public void loadChoiceBox() {
        LinkedHashSet<String> exercises = databaseModuleWorkout.getAllUserExercises();
        if(exercises.size() > 0) {
            choiceBoxExercise.setItems(FXCollections.observableArrayList(exercises));
            choiceBoxExercise.getSelectionModel().selectedItemProperty().addListener(new MyChoiceBoxListener(choiceBoxExercise));
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
            createRepetitionsChart(newValue);
            createWeightChart(newValue);
        }
    }

    /**
     * Loads all data for chart from {@code String} argument and displays it in the chart
     * @param exercise a {@code String} exercise
     */
    private void createRepetitionsChart(String exercise) {
        lineChartRepetitions.getData().clear();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        Map<String, Integer> dataForChart = databaseModuleWorkout.getAllRepetitionsByExercise(exercise);
        if(dataForChart == null) {
            System.out.println("No data");
        } else {
            System.out.println("Repetitions chart" + dataForChart.toString());
            for(Map.Entry<String, Integer> data : dataForChart.entrySet()) {
                //Date is too big, temporary solution for now
                String date = data.getKey().substring(0, 5);
                Integer repetitions = data.getValue();
                series.getData().add(new XYChart.Data<>(date, repetitions));
            }
            lineChartRepetitions.getData().add(series);
        }
    }

    /**
     * Loads all data for chart from {@code String} argument and displays it in the chart
     * @param exercise a {@code String} exercise
     */
    private void createWeightChart(String exercise) {
        lineChartWeight.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Map<String, Double> dataForChart = databaseModuleWorkout.getAllWeightByExercise(exercise);
        if(dataForChart == null) {
            System.out.println("no data");
        } else {
            System.out.println("Weight chart" + dataForChart.toString());
            for(Map.Entry<String, Double> data : dataForChart.entrySet()) {
                //Date is too big, temporary solution for now
                String date = data.getKey().substring(0, 5);
                Double weight = data.getValue();
                series.getData().add(new XYChart.Data<>(date, weight));
            }
            lineChartWeight.getData().add(series);
        }

    }
}
