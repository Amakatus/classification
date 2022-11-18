package app.graphics.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScatterChartController extends AbstractController {
	@FXML protected Label datasetTitle;

	@FXML
    public void initialize() {
        System.out.println("init new tab");
    }
	
	public void setTitle(String title) {
		this.datasetTitle.setText(title);
	}
}