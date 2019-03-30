package sample.Controllers.SceneControllers.Trainer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;

<<<<<<< HEAD:src/sample/Controllers/SceneControllers/UserHomeScreenController.java
import java.awt.*;


public class UserHomeScreenController extends Controller {

=======
public class TrainerHomeScreenController extends Controller {
>>>>>>> d6e96529a78cb977b3a98df4eda4fc7c0d48ca6e:src/sample/Controllers/SceneControllers/Trainer/TrainerHomeScreenController.java
    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;





    @FXML
    public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

}

