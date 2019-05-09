package sample.Dialogs;

import javafx.scene.control.Alert;


public class DeleteWorkoutDialog extends Alert {

    public DeleteWorkoutDialog(AlertType alertType) {
        super(alertType);
        setTitle("Delete workout");
        setHeaderText("Are you sure you want to delete workout?");
        setContentText("This action can not be undone!");
    }
}
