package sample.Controllers.FragmentControllers.HomeSceneFragments;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutUsFragment {
    @FXML
    private void onHyperLinkPaypalPressed() {
        Application application = new Application() {
            @Override
            public void start(Stage primaryStage) {
            }
        };
        application.getHostServices().showDocument("https://paypal.me/pools/c/8dO80GARuK");
    }
}
