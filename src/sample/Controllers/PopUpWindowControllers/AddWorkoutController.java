package sample.Controllers.PopUpWindowControllers;

import com.sun.org.apache.bcel.internal.generic.LADD;
import db.DTO.Workout;
import db.DatabaseModuleUser;
import db.DatabaseModuleWorkout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import sample.Controllers.FragmentControllers.WorkoutSceneFragments.WorkoutsFragmentController;
import sample.Session;

import javax.xml.soap.Text;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddWorkoutController {

    @FXML
    private TextField textFieldNameOfExcersise;
    @FXML
    private TextField textFieldRepetitions;
    @FXML
    private TextField textFieldWeight;
    @FXML
    private Label labelEmptyFieldError;
    @FXML
    private Label labelWorkoutAdded;


    @FXML
    private void onButtonSavePressed() {
        resetAllFeedback();
        boolean badInput=false;
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        DatabaseModuleWorkout databaseModuleWorkout = new DatabaseModuleWorkout();

        if(textFieldNameOfExcersise.getText().equals("")){
            badInput=true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldNameOfExcersise);

        }if (textFieldRepetitions.getText().equals("")||!numberOrNot(textFieldRepetitions.getText())){
            badInput=true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldRepetitions);
        }
        if (textFieldWeight.getText().equals("")|| !numberOrNot(textFieldWeight.getText()) ){
            badInput=true;
            labelEmptyFieldError.setVisible(true);
            displayFeedBack(textFieldWeight);
        }
        if (!badInput){
            databaseModuleWorkout.insertWorkout(textFieldNameOfExcersise.getText(),Integer.parseInt(textFieldRepetitions.getText()),Integer.parseInt(textFieldWeight.getText()),date);
            labelWorkoutAdded.setVisible(true);


        }



    }




    private boolean numberOrNot(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    private void displayFeedBack(TextField textField) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.color(1, 0, 0));
        textField.setEffect(dropShadow);
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(0);
        dropShadow.setColor(Color.color(0, 0, 0));
       textFieldNameOfExcersise.setEffect(dropShadow);
       textFieldRepetitions.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        labelEmptyFieldError.setVisible(false);
        labelWorkoutAdded.setVisible(false);

    }
}
