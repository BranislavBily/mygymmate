package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;

//Class that changes scenes
public class Controller {
    void setScene(Scene scene, String fxml, String title) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, fxml, title);
    }

    private void changeScene(Stage stage, String fxml, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setScene(Scene scene, String fxml, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, fxml, user);
    }

    private void changeScene(Stage stage, String fxml, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            RegisterInfoController registerInfoController = loader.getController();
            registerInfoController.setUser(user);
            stage.setScene(scene);
            stage.setTitle(user.getUsername());
            stage.setResizable(false);
            stage.show();
            registerInfoController.setLabelUsername();
            registerInfoController.setChoiceBoxItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
