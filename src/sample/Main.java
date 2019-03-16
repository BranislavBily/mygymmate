package sample;

import db.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Modules.ModuleTitles;

import java.sql.Connection;

public class Main extends Application {

    private static Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
        primaryStage.setTitle(ModuleTitles.LOG_IN);
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
