package sample.Controllers.SceneControllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Controllers.FeedbackController;
import sample.Controllers.SceneControllers.Admin.AdminHomeSceneController;
import sample.Controllers.SceneControllers.Trainee.TraineeHomeSceneController;
import sample.Controllers.SceneControllers.Trainer.TrainerHomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceTitles;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Controller for methods used in all scenes
 */
public abstract class Controller extends FeedbackController {

    protected void setSceneToLogin(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ResourceFXML.LOGIN, ResourceTitles.LOG_IN);
    }

    protected void setSceneToTraineeHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTraineeHomeScene(stage);
    }

    protected void setSceneToTrainerHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTrainerHomeScene(stage);
    }

    protected void setSceneToAdminHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToAdminHomeScene(stage);
    }

    //This method is called when sending User is not necessary
    void changeScene(Stage stage, String fxml, String title) {
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

    private void changeSceneToTraineeHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.TRAINEE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(ResourceTitles.TRAINEE_HOME_SCENE);
            stage.show();
            TraineeHomeSceneController traineeHomeSceneController = loader.getController();
            traineeHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToTrainerHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.TRAINER_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ResourceTitles.TRAINER_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            TrainerHomeSceneController trainerHomeSceneController = loader.getController();
            trainerHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToAdminHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.ADMIN_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ResourceTitles.ADMIN_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            AdminHomeSceneController adminHomeSceneController = loader.getController();
            adminHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setLabelUsername(Label labelUsername, String username) {
        labelUsername.setText(username);
        labelUsername.setLayoutX(140 - ((username.length() + 1) * 5.14));
    }



    @FXML
    private void onHyperLinkPaypalPressed() {
        Application application = new Application() {
            @Override
            public void start(Stage primaryStage) {
            }
        };
        application.getHostServices().showDocument("https://paypal.me/pools/c/8dO80GARuK");
    }

    public void sendEmail(String email,String subject, String messageem) {

        final String username = "testemailsending29@gmail.com";
        final String password = "Heslo+123456";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testemailsending29@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setContent(messageem, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Email was send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    protected abstract void addingNotification(String email);
}


