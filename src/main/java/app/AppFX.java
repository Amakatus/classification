package app;

import app.graphics.views.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new View("index", "CloudyClass").show();
    }
}
