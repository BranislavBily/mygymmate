package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Fragment extends AnchorPane {
    public Fragment(String FXML) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(FXML));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
