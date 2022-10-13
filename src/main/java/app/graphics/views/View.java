package main.java.app.graphics.views;


import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.app.graphics.controllers.Controller;


public class View {
	
	private Stage stage;
	private Controller controller;
	
	public View(String viewName, String windowName, int width, int height) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/test.fxml"));
		Parent root;
		try { root = loader.load(); } catch (IOException e) { e.printStackTrace(); return; }
		this.controller = loader.getController();
		this.stage = new Stage();
		this.stage.setTitle(windowName);
		if(width > 0 && height > 0) {			
			this.stage.setScene(new Scene(root, width, height));
		} else {
			this.stage.setScene(new Scene(root));
		}
		this.stage.setResizable(false);
		//this.controller.setView(this);
	}
	
	public View(String viewPath, String windowName) {
		this(viewPath, windowName,0,0);
	}
	
	public View(String viewPath) {
		this(viewPath, viewPath);
	}
	

	public Stage getStage() {
		return this.stage;
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public void show() {
		this.getStage().show();
	}
}

