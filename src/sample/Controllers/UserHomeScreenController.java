package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Moduls.ModulFXML;
import sample.Moduls.ModuleTitles;


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
        setScene(buttonLogOut.getScene(), ModulFXML.LOGIN, ModuleTitles.LOG_IN);
    }

}
