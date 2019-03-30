package sample.Controllers.SceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Modules.ModuleTitles;



public class MeasureController extends Controller {




@FXML
    private Button buttonMeasures;
@FXML
    private Button buttonProgress;
@FXML
    private Label usernameLabel;



@FXML
private void onGoBackImagePressed(){
    setScene(buttonMeasures.getScene(), "../../FXML/traineeHomeScreen.fxml", ModuleTitles.USER_HOME_SCREEN);


}

}
