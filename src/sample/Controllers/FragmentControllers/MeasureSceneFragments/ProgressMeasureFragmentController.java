package sample.Controllers.FragmentControllers.MeasureSceneFragments;

import db.DatabaseModuleMeasurements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import sample.Interfaces.Progress;

import java.util.Map;

public class ProgressMeasureFragmentController implements Progress {

    @FXML
    private ChoiceBox<String> choiceBoxBodyPart;
    @FXML
    private LineChart<String, Double> lineChartSizeTop;
    @FXML
    private LineChart<String, Double> lineChartSizeBottom;

    private DatabaseModuleMeasurements databaseModuleMeasurements;

    public void onCreate(){
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        loadChoiceBox();
    }


    @Override
    public void loadChoiceBox() {
        choiceBoxBodyPart.setItems(FXCollections.observableArrayList(
                "Arms", "Forearms", "Shoulders", "Waist", "Chest", "Thighs", "Calves")
        );
        choiceBoxBodyPart.getSelectionModel().selectedItemProperty().addListener(new MyChoiceBoxListener(choiceBoxBodyPart));
    }

    private class MyChoiceBoxListener implements ChangeListener<String> {
        final ChoiceBox<String> cb;
        MyChoiceBoxListener(ChoiceBox<String> cb) {
            this.cb = cb;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            System.out.println(newValue);
            if(newValue.equals("Shoulders") || newValue.equals("Waist") || newValue.equals("Chest")) {
                lineChartSizeBottom.setVisible(false);
                lineChartSizeTop.setTitle(newValue);
                loadTopChart(newValue);
            } else if(newValue.equals("Calves")) {
                lineChartSizeBottom.setVisible(true);
                String leftBodyPart = "LeftCalf";
                String rightBodyPart = "RightCalf";
                lineChartSizeTop.setTitle(leftBodyPart);
                lineChartSizeBottom.setTitle(rightBodyPart);
                loadBothCharts(leftBodyPart, rightBodyPart);
            } else {
                lineChartSizeBottom.setVisible(true);
                String leftBodyPart = "Left" + newValue.replace("s", "");
                String rightBodyPart = "Right" + newValue.replace("s", "");
                lineChartSizeTop.setTitle(leftBodyPart);
                lineChartSizeBottom.setTitle(rightBodyPart);
                loadBothCharts(leftBodyPart, rightBodyPart);
            }
        }
    }

    private void loadTopChart(String bodyPart) {
        lineChartSizeTop.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Map<String, Double> dataForChart = databaseModuleMeasurements.getAllMeasurementsByBodyPart(bodyPart);
        if(dataForChart == null) {
            System.out.println("No data");
        } else {
            System.out.println("Top chart " + dataForChart.toString());
            for (Map.Entry<String, Double> data : dataForChart.entrySet()) {
                //Date is too big, temporary solution for now
                String date = data.getKey().substring(5, 10);
                Double measurement = data.getValue();
                series.getData().add(new XYChart.Data<>(date, measurement));
            }
            lineChartSizeTop.getData().add(series);
        }
    }

    private void loadBottomChart(String bodyPart) {
        lineChartSizeBottom.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Map<String, Double> dataForChart = databaseModuleMeasurements.getAllMeasurementsByBodyPart(bodyPart);
        if(dataForChart == null) {
            System.out.println("No data");
        } else {
            System.out.println("Bottom chart " + dataForChart.toString());
            for (Map.Entry<String, Double> data : dataForChart.entrySet()) {
                //Date is too big, temporary solution for now
                String date = data.getKey().substring(5, 10);
                Double measurement = data.getValue();
                series.getData().add(new XYChart.Data<>(date, measurement));
            }
            lineChartSizeBottom.getData().add(series);
        }
    }

    private void loadBothCharts(String leftBodyPart, String rightBodyPart) {
        loadTopChart(leftBodyPart);
        loadBottomChart(rightBodyPart);
    }





}
