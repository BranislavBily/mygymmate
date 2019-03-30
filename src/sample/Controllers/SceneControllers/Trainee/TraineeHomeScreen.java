package sample.Controllers.SceneControllers.Trainee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.User;


public class TraineeHomeScreen extends Controller {


    private Trainee trainee;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;

    @FXML
    private Label labelUsername;

    public Label getLabelUsername() {
        return labelUsername;
    }

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    @FXML
   public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

@FXML
    private void onButtonWorkoutPressed(){ setScene(buttonWorkout.getScene(),ModuleFXML.WORKOUTS_HOME_SCREEN,ModuleTitles.USER_HOME_SCREEN);}



}
