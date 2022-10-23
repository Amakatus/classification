package app.graphics.views;


import java.io.IOException;

import app.exceptions.NoControllerException;
import app.graphics.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class View {
	
	protected Stage stage;
	protected Controller controller;
	protected Parent loadedResource;
	
	public View(String viewName, String windowName, int width, int height) throws NoControllerException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+viewName+".fxml"));
		try {
			this.loadedResource = loader.load();
			if(this.setupController(loader)) {
				setupStage(windowName, width, height, loadedResource);
			} else { throw new NoControllerException("Please, register a valid controler for " + viewName); }
		} catch (IOException e) { e.printStackTrace();}
	}

	private void setupStage(String windowName, int width, int height, Parent root) {
		this.stage = new Stage();
		this.stage.setTitle(windowName);
		if(width > 0 && height > 0) {			
			this.stage.setScene(new Scene(root, width, height));
		} else {
			this.stage.setScene(new Scene(root));
		}
		this.stage.setResizable(false);
	}
	
	private boolean setupController(FXMLLoader loader) {
		Controller controller = loader.getController();
		if(controller == null) return false;
		this.controller = controller;
		this.controller.setView(this);
		return true;
	}

	public View(String viewPath, String windowName) throws NoControllerException {
		this(viewPath, windowName,0,0);
	}
	
	public View(String viewPath) throws NoControllerException {
		this(viewPath, viewPath);
	}
	
	public Stage getStage() { return this.stage; }
	public Controller getController() { return this.controller; }
	public Parent getLoadedResource() { return this.loadedResource; }
	
	public void show() {
		this.stage.show();
	}

	public void closeStage() {
		this.stage.close();
	}
}

