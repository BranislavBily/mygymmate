package sample.Dialogs;

import javafx.scene.control.Alert;

public class AddTraineeDialog extends Alert {

    public AddTraineeDialog(AlertType alertType, String name) {
        super(alertType);
        setTitle("Add trainee");
        setHeaderText("Are you sure you want to add " + name + " as your trainee?");
        setContentText(null);
    }

}
