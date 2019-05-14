package sample.Controllers.FragmentControllers.DietSceneFragments;

import db.DTO.Weight;
import db.DatabaseModuleWeight;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Controllers.FeedbackController;
import sample.Session;

import java.util.Map;


public class WeightFragmentController extends FeedbackController {



    @FXML
    private LineChart lineChartWeight;
    @FXML
    private Label labelLastUpdate;

    @FXML
    private Label labelInfo;

    @FXML
   private  TextField textFieldWeight;

    @FXML
    private Label labelInvalidInput;

    private Weight weight;
    DatabaseModuleWeight databaseModuleWeight=new DatabaseModuleWeight();

    public void onCreate() {
        loadDataIntoLabels();
        createWeightChart();
    }

    @FXML
    private void onButtonUpdateWeightPressed(){
            resetAllFeedback();
        if(textFieldWeight.getText().equals("")|| !isDouble(textFieldWeight.getText())){
            displayFeedBack(textFieldWeight);
            labelInvalidInput.setVisible(true);
        }else {
            databaseModuleWeight.insertWeight(Session.getUserID(),Double.parseDouble(textFieldWeight.getText()));
            loadDataIntoLabels();
            createWeightChart();
        }

    }

    private void loadDataIntoLabels(){

        weight=databaseModuleWeight.getUserWeight();
        System.out.println(weight.getWeight());
        labelInfo.setText("    Your last weight was "+weight.getWeight()+"kg.    ");
        labelLastUpdate.setText("Last Update "+weight.getDate());

    }

    private void resetAllFeedback(){

        labelInvalidInput.setVisible(false);
        textFieldWeight.setEffect(getCleanDropShadow());
    }


    private void createWeightChart() {
        lineChartWeight.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Map<String, Double> dataForChart = databaseModuleWeight.getAllWeight();
        if (dataForChart == null) {
            System.out.println("no data");
        } else {
            System.out.println("Weight chart" + dataForChart.toString());
            for (Map.Entry<String, Double> data : dataForChart.entrySet()) {
                String date = data.getKey();
                Double weight = data.getValue();
                series.getData().add(new XYChart.Data<>(date, weight));

            }
            lineChartWeight.getData().add(series);
        }

    }
}
