package app.components;

import javafx.scene.control.Alert;

public interface AlertFactory {
    static Alert createErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText("Oops! An error occured... :(");
        alert.setContentText(message);
        return alert;
    }
    
    static Alert createSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Everything is ok");
        alert.setHeaderText("Yea ! All went good.");
        alert.setContentText(message);
        return alert;
    }
}
