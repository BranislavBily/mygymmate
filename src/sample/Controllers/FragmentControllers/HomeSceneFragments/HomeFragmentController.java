package sample.Controllers.FragmentControllers.HomeSceneFragments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Controllers.SceneControllers.HomeSceneController;

public class HomeFragmentController extends HomeSceneController {

    public void onCreate() {

    }

    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonWithMeasure;

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

}
