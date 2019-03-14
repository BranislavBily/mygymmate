package sample;

import db.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Moduls.ModulTitles;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;

public class Main extends Application {

    private static Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));

        URL iconURL = Main.class.getResource("Images/logoIcon.png");
        java.awt.Image image = new ImageIcon(iconURL).getImage();

        //Mac users
        com.apple.eawt.Application.getApplication().setDockIconImage(image);

        //Windows users
//        primaryStage.getIcons().add(new Image("Images/logoIcon.png"));

        primaryStage.setTitle(ModulTitles.LOG_IN);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Creates connection, if not successful application closes down
        connection = SqliteConnection.connector();
        if(connection == null) {
            System.exit(1);
        }
        launch(args);
    }
}
