package sample.Controllers.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.User;


public class UserHomeScreenController extends Controller {


    private User user;
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;


@FXML
   private void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

@FXML
    private void onButtonWorkoutPressed(){ setScene(buttonWorkout.getScene(),ModuleFXML.WORKOUTS_HOME_SCREEN,ModuleTitles.USER_HOME_SCREEN);}

}