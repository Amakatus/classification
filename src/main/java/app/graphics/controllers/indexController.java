package app.graphics.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class indexController extends Controller {
	
	@FXML MFXButton toolbarGitlab;
	
	@FXML void toolbarGitlabOnClick(MouseEvent mouseEvent) {
		System.out.println("Clicked on tollbarGitlab " + mouseEvent);
		System.out.println("Should open gitlab.");
	}
}