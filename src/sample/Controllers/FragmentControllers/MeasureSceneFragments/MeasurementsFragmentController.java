package sample.Controllers.FragmentControllers.MeasureSceneFragments;

import db.DTO.Measurement;
import db.DatabaseModuleMeasurements;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Controllers.FeedbackController;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MeasurementsFragmentController extends FeedbackController {

    private DatabaseModuleMeasurements databaseModuleMeasurements;

    @FXML
    private TextField textFieldRightArm;
    @FXML
    private TextField textFieldLeftArm;
    @FXML
    private TextField textFieldRightForeArm;
    @FXML
    private TextField textFieldLeftForeArm;
    @FXML
    private TextField textFieldRightThigh;
    @FXML
    private TextField textFieldLeftThigh;
    @FXML
    private TextField textFieldRightCalf;
    @FXML
    private TextField textFieldLeftCalf;
    @FXML
    private TextField textFieldShoulders;
    @FXML
    private TextField textFieldChest;
    @FXML
    private TextField textFieldWaist;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonSave;
    @FXML
    private Label labelAlreadyMeasurement;
    @FXML
    private Label labelMeasurementsFrom;


    public void onCreate() {
        databaseModuleMeasurements = new DatabaseModuleMeasurements();
        loadDataIntoControls();
        buttonSave.setDisable(true);
        setDisabledTextFields();
    }

    /**
     * Loads data from database into controls
     */
    private void loadDataIntoControls() {
        Measurement measurement = databaseModuleMeasurements.getUserMeasurement();
        textFieldChest.setText(String.valueOf(measurement.getChest()));
        textFieldLeftArm.setText(String.valueOf(measurement.getLeftArm()));
        textFieldLeftCalf.setText(String.valueOf(measurement.getLeftCalf()));
        textFieldLeftForeArm.setText(String.valueOf(measurement.getLeftForeArm()));
        textFieldLeftThigh.setText(String.valueOf(measurement.getLeftThigh()));
        textFieldRightArm.setText(String.valueOf(measurement.getRightArm()));
        textFieldRightCalf.setText(String.valueOf(measurement.getRightCalf()));
        textFieldRightThigh.setText(String.valueOf(measurement.getRightThigh()));
        textFieldRightForeArm.setText(String.valueOf(measurement.getRightForeArm()));
        textFieldShoulders.setText(String.valueOf(measurement.getShoulders()));
        textFieldWaist.setText(String.valueOf(measurement.getWaist()));
        labelMeasurementsFrom.setText("Measurements from " + measurement.getFullDate());
    }

    /**
     * Loads data from TextField, returns {@code Measurement} with that data
     *
     * @return {@code Measurement} with data from TextFields
     */
    private Measurement loadDataIntoMeasurement() {
        Measurement measurement = new Measurement();
        measurement.setRightArm(Double.parseDouble(textFieldRightArm.getText()));
        measurement.setLeftArm(Double.parseDouble(textFieldLeftArm.getText()));
        measurement.setRightForeArm(Double.parseDouble(textFieldRightArm.getText()));
        measurement.setLeftForeArm(Double.parseDouble(textFieldLeftForeArm.getText()));
        measurement.setRightThigh(Double.parseDouble(textFieldRightThigh.getText()));
        measurement.setLeftThigh(Double.parseDouble(textFieldLeftThigh.getText()));
        measurement.setRightCalf(Double.parseDouble(textFieldRightCalf.getText()));
        measurement.setLeftCalf(Double.parseDouble(textFieldLeftCalf.getText()));
        measurement.setChest(Double.parseDouble(textFieldChest.getText()));
        measurement.setShoulders(Double.parseDouble(textFieldShoulders.getText()));
        measurement.setWaist(Double.parseDouble(textFieldWaist.getText()));
        measurement.setDate(getTodayDate());
        System.out.println(measurement.toString());
        return measurement;
    }

    /**
     * Returns {@code String} today's date  with formatting for the database
     *
     * @return {@code String} today's date  with formatting for the database
     */
    private String getTodayDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private void setDisabledTextFields() {
        textFieldWaist.setDisable(true);
        textFieldShoulders.setDisable(true);
        textFieldRightForeArm.setDisable(true);
        textFieldRightThigh.setDisable(true);
        textFieldRightCalf.setDisable(true);
        textFieldRightArm.setDisable(true);
        textFieldLeftThigh.setDisable(true);
        textFieldLeftCalf.setDisable(true);
        textFieldLeftForeArm.setDisable(true);
        textFieldLeftArm.setDisable(true);
        textFieldChest.setDisable(true);
    }

    private void enableTextFields() {
        textFieldWaist.setDisable(false);
        textFieldShoulders.setDisable(false);
        textFieldRightForeArm.setDisable(false);
        textFieldRightThigh.setDisable(false);
        textFieldRightCalf.setDisable(false);
        textFieldRightArm.setDisable(false);
        textFieldLeftThigh.setDisable(false);
        textFieldLeftCalf.setDisable(false);
        textFieldLeftForeArm.setDisable(false);
        textFieldLeftArm.setDisable(false);
        textFieldChest.setDisable(false);
    }

    /**
     * Checks TextFields, returns {@code true} if all input is good, {@code false} if there's an error in input
     *
     * @return {@code true} if all input is good, {@code false} if there's an error in input
     */
    private boolean checkTextFields() {
        boolean goodInput = true;
        if (textFieldWaist.getText().isEmpty() || !isDouble(textFieldWaist.getText())) {
            displayFeedBack(textFieldWaist);
        }
        if (textFieldShoulders.getText().isEmpty() || !isDouble(textFieldShoulders.getText())) {
            displayFeedBack(textFieldShoulders);
            goodInput = false;
        }
        if (textFieldRightArm.getText().isEmpty() || !isDouble(textFieldRightArm.getText())) {
            displayFeedBack(textFieldRightArm);
            goodInput = false;
        }
        if (textFieldLeftArm.getText().isEmpty() || !isDouble(textFieldLeftArm.getText())) {
            displayFeedBack(textFieldLeftArm);
            goodInput = false;
        }
        if (textFieldChest.getText().isEmpty() || !isDouble(textFieldChest.getText())) {
            displayFeedBack(textFieldChest);
            goodInput = false;
        }
        if (textFieldRightThigh.getText().isEmpty() || !isDouble(textFieldRightThigh.getText())) {
            displayFeedBack(textFieldRightThigh);
            goodInput = false;
        }
        if (textFieldLeftThigh.getText().isEmpty() || !isDouble(textFieldLeftThigh.getText())) {
            displayFeedBack(textFieldLeftThigh);
            goodInput = false;
        }
        if (textFieldRightCalf.getText().isEmpty() || !isDouble(textFieldRightCalf.getText())) {
            displayFeedBack(textFieldRightCalf);
            goodInput = false;
        }
        if (textFieldLeftCalf.getText().isEmpty() || !isDouble(textFieldLeftCalf.getText())) {
            displayFeedBack(textFieldLeftCalf);
            goodInput = false;
        }
        if (textFieldRightForeArm.getText().isEmpty() || !isDouble(textFieldRightForeArm.getText())) {
            displayFeedBack(textFieldRightForeArm);
            goodInput = false;
        }
        if (textFieldLeftForeArm.getText().isEmpty() || !isDouble(textFieldLeftForeArm.getText())) {
            displayFeedBack(textFieldLeftForeArm);
            goodInput = false;
        }
        return goodInput;
    }

    @FXML
    private void onButtonUpdatePressed() {
        enableTextFields();
        labelAlreadyMeasurement.setVisible(false);
        buttonSave.setDisable(false);
        buttonUpdate.setDisable(true);
    }

    @FXML
    private void onButtonSavePressed() {
        if (checkTextFields()) {
            insertMeasurement();
            buttonSave.setDisable(true);
            buttonUpdate.setDisable(false);
            setDisabledTextFields();
        }
    }

    private void insertMeasurement() {
        Measurement measurement = loadDataIntoMeasurement();
        //If measurement was already added today
        if (databaseModuleMeasurements.measurementAlreadyAddedToday(measurement)) {
            System.out.println("Measurement already added today");
            //Update todays measurement
            if (databaseModuleMeasurements.updateMeasurement(measurement)) {
                System.out.println("Measurement updated!");
                loadDataIntoControls();
            } else {
                System.out.println("Error while updating");
            }
            //If not, insert measurement into the database
        } else if (databaseModuleMeasurements.insertMeasures(measurement)) {
            System.out.println("Measurement saved!");
            loadDataIntoControls();
        } else {
            System.out.println("Error while inserting measurement");
            loadDataIntoControls();
        }
    }
}
