package app.graphics.controllers;

import app.exceptions.NoControllerException;
import app.graphics.views.View;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class IndexController extends Controller {
	int numberTabs = 1;
	@FXML MFXButton newTabButton;
	@FXML TabPane tabPane;
	
	@Override
	public void initialize() {}
	
	@FXML void createNewTab(MouseEvent mouseEvent) {
		Tab newTab;
		try {
			View newDatasetView = new View("dataset");
			newTab = new Tab("Tab " + this.numberTabs, (Node) newDatasetView.getLoadedResource());
			DatasetController dsController = (DatasetController) newDatasetView.getController();
			dsController.setTitle("Dataset #"+this.numberTabs);
			this.tabPane.getTabs().add(newTab);
			this.numberTabs++;
		} catch (NoControllerException e) {e.printStackTrace();}
	}
}