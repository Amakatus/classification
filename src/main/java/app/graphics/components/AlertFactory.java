package app.graphics.components;

import javafx.scene.control.Alert;

public interface AlertFactory {
    static Alert createErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText("Oops! An error occured... :(");
        alert.setContentText(message);
        return alert;
    }
}
