package sample.Controllers.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import javax.swing.text.html.ImageView;

public class WorkoutController extends Controller {




@FXML
    private Button buttonWorkouts;
@FXML
    private Button buttonProgress;
@FXML
    private Label usernameLabel;



@FXML
private void onGoBackImagePressed(){
    setScene(buttonWorkouts.getScene(), "../../FXML/traineeHomeScreen.fxml", ModuleTitles.USER_HOME_SCREEN);


}

}
