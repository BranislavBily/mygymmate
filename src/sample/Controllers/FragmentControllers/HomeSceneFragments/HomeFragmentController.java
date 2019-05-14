package sample.Controllers.FragmentControllers.HomeSceneFragments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Quotes;

public class HomeFragmentController extends HomeSceneController {

    public void onCreate() {
            setRandomQuote();
    }

    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonWithMeasure;

    @FXML
    private Label labelQuote;

    @FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene());
    }

    @FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonWithMeasure.getScene());
    }

    @FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene());
    }

    public void setRandomQuote(){
        Quotes quotes=new Quotes();
        labelQuote.setText(quotes.getRandomQuote());
        labelQuote.setLayoutX(368-labelQuote.getText().length()*3);

    }

}
