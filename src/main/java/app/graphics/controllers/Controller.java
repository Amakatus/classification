package app.graphics.controllers;

import app.graphics.models.Model;
import app.graphics.views.View;

public abstract class Controller implements IFXController {
	protected Model model;
	protected View view;
	
	protected Controller(View view) {
		this.view = view;
	}

	protected Controller() {
		this(null);
	}
	
	public View getView() {
		return this.view;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public Model getModel() {
		return this.model;
	}
	
	public void closeView() {
		if(this.view.getStage() != null) {			
			this.view.closeStage();
		}
	}
}

