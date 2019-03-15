package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Moduls.ModulTitles;

import javax.swing.*;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));

        URL iconURL = Main.class.getResource("Images/logoIcon.png");
        java.awt.Image image = new ImageIcon(iconURL).getImage();
        primaryStage.setTitle(ModulTitles.LOG_IN);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
