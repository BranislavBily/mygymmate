package sample;

import db.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Resources.ResourceTitles;

import java.sql.Connection;

public class Main extends Application {

    private static Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
        primaryStage.setTitle(ResourceTitles.LOG_IN);
        primaryStage.getIcons().add(new Image("sample/Images/logoIcon.png"));
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
