package sample.Controllers.FragmentControllers.DietSceneFragments;

import db.DTO.UserDietInfo;
import db.DatabaseModuleDiet;
import db.DatabaseModuleWeight;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;

import sample.Controllers.FeedbackController;

public class BMIFragmentController extends FeedbackController {


    @FXML
    private Label labelBMI;
    @FXML
    private Label labelArrow;
    @FXML
    private Label labelInvalidInputHeight;
    @FXML
    private Label labelInvalidInputWeight;
    @FXML
    private Label labelInfo;
    @FXML
    private Button buttonManual;
    @FXML
    private Button buttonYour;
    @FXML
    private TextField textFieldHeight;
    @FXML
    private TextField textFieldWeight;


    DatabaseModuleDiet databaseModuleDiet = new DatabaseModuleDiet();
    UserDietInfo userDietInfo = new UserDietInfo();

    public void onCreate() {
        userDietInfo = databaseModuleDiet.getUserDietInfo();
        labelBMI.setText("" + Math.round(getMyBMI(userDietInfo.getWeight(), userDietInfo.getHeight()) * 10.0) / 10.0);
        setArrow(Math.round(getMyBMI(userDietInfo.getWeight(), userDietInfo.getHeight()) * 10.0) / 10.0);
        setLabelInfo(userDietInfo.getWeight(), userDietInfo.getHeight());
    }


    public double getMyBMI(double weight, double height) {

        return weight / ((height / 100) * (height / 100));
    }

    private void setArrow(double bmi) {

        if (bmi > 35) {
            labelArrow.setLayoutX(595);
        } else if (bmi < 16) {
            labelArrow.setLayoutX(25);
        } else {
            labelArrow.setLayoutX((int) bmi * 30.5 - 447);
        }


    }

    @FXML
    private void onButtonManualPressed() {

        resetAllFeedback();
        boolean error = false;
        if ((textFieldHeight.getText().equals("") || !isDouble(textFieldHeight.getText())) || Double.parseDouble(textFieldHeight.getText()) > 250 || Double.parseDouble(textFieldHeight.getText()) < 120) {
            displayFeedBack(textFieldHeight);
            labelInvalidInputHeight.setVisible(true);
            error = true;
        }
        if ((textFieldWeight.getText().equals("") || !isDouble(textFieldWeight.getText())) || Double.parseDouble(textFieldWeight.getText()) > 500 || Double.parseDouble(textFieldWeight.getText()) < 35) {
            displayFeedBack(textFieldWeight);
            labelInvalidInputWeight.setVisible(true);
            error = true;
        }

        if (!error) {
            setManualBMI(Double.parseDouble(textFieldWeight.getText()), Double.parseDouble(textFieldHeight.getText()));
        }

    }

    private void resetAllFeedback() {
        labelInvalidInputWeight.setVisible(false);
        labelInvalidInputHeight.setVisible(false);
        DropShadow dropShadow = getCleanDropShadow();
        textFieldWeight.setEffect(dropShadow);
        textFieldHeight.setEffect(dropShadow);

    }

    @FXML
    private void onButtonYourPressed() {
        onCreate();

    }

    private void setManualBMI(double weight, double height) {

        labelBMI.setText("" + Math.round(getMyBMI(weight, height) * 10.0) / 10.0);
        setArrow(Math.round(getMyBMI(weight, height) * 10.0) / 10.0);
        setLabelInfo(weight, height);

    }

    private void setLabelInfo(double weight, double height) {
        double bmi = weight / ((height / 100) * (height / 100));
        double kg;
        double weightToGo;
        System.out.println(bmi);
        if (bmi >= 19 && bmi < 25) {
            labelInfo.setText("   You are in healthy state.   ");
            labelInfo.setLayoutX(225);
        } else if (bmi >= 25) {
            kg = ((height / 100) * (height / 100)) * 25;
            weightToGo = weight - kg;
            labelInfo.setText("   You need to lose " + Math.round(weightToGo * 10.0) / 10.0 + " kg to be in healthy state.   ");
            labelInfo.setLayoutX(143);

        } else if (bmi < 19) {
            kg = ((height / 100) * (height / 100)) * 19;
            weightToGo = weight - kg;
            labelInfo.setText("   You need to gain " + Math.abs(Math.round(weightToGo * 10.0) / 10.0) + " kg to be in healthy state.   ");
            labelInfo.setLayoutX(143);
        }

    }


}
