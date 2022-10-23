package app;

import javafx.application.Application;
import javafx.stage.Stage;
import app.exceptions.NoControllerException;
import app.graphics.views.View;
 
public class AppFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	View view;
		try {
			view = new View("index", "CloudyClass");
		} catch (NoControllerException e) {
			e.getMessage();
			return;
		}
		view.getStage().show();
    }
}
