package sample.Controllers.FragmentControllers.DietSceneFragments;

import db.DTO.Weight;
import db.DatabaseModuleWeight;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Controllers.FeedbackController;
import sample.Session;


public class WeightFragmentController extends FeedbackController {


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
}
