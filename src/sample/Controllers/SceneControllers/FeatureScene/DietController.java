package sample.Controllers.SceneControllers.FeatureScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.Controller;
import sample.Modules.ModuleTitles;

public class DietController extends Controller {




@FXML
    private Button buttonDiets;
@FXML
    private Button buttonWieight;
@FXML
    private Button buttonBMI;
@FXML
    private Label usernameLabel;




@FXML
private void onGoBackImagePressed(){
    setScene(buttonDiets.getScene(), "../../FXML/traineeHomeScene.fxml", ModuleTitles.USER_HOME_SCENE);


}

}
