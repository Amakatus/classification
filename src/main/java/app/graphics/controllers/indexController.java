package app.graphics.controllers;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class indexController extends Controller {
	int numberTabs = 0;
	@FXML MFXButton newTabButton;
	@FXML TabPane tabPane;
	
	void init() {
		System.out.println("Test");
	}
	
	@FXML void createNewTab(MouseEvent mouseEvent) {
		Tab newTab;
		try {
			newTab = new Tab("Tab " + this.numberTabs, (Node) FXMLLoader.load(this.getClass().getResource("/fxml/dataset.fxml")));
			this.tabPane.getTabs().add(newTab);
			this.numberTabs++;
		} catch (IOException e) {e.printStackTrace();}
	}
}