package sample.Controllers.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;

import java.awt.*;


public class UserHomeScreenController extends Controller {

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
        setScene(buttonLogOut.getScene(), ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

}
