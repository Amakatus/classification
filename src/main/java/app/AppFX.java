package main.java.app;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.app.graphics.views.View;
 
public class AppFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	View view = new View("test", "Test App");
    	view.getStage().show();
    }
}
