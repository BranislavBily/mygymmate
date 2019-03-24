package sample.Controllers.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;


public class UserHomeScreenController extends Controller {

    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;



   public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }
}
