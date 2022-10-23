package app.graphics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class datasetController extends Controller {
	@FXML Label datasetTitle;
	
	@FXML
    public void initialize() {
        System.out.println("init new tab");
        this.datasetTitle.setText("New tab " + Math.random());
    }
}