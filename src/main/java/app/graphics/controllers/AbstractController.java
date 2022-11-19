package app.graphics.controllers;

import app.graphics.models.AbstractModel;
import app.graphics.models.Observer;
import app.graphics.views.View;
import app.utils.Logger;

public abstract class AbstractController implements IFXController, Observer {
	protected AbstractModel model;
	protected View view;
	
	protected AbstractController(View view) {
		this.view = view;
	}

	protected AbstractController() {
		this(null);
	}
	
	public View getView() {
		return this.view;
	}
	
	public void setView(View view) {
		this.view = view;
	}

	public void update() {
		Logger.log("Controller " + this.getClass().getSimpleName() + " has received update but haven't done anything.");
	}

	public void setModel(AbstractModel model) {
		this.model = model;
		model.attach(this);
	}
	
	public AbstractModel getModel() {
		return this.model;
	}
	
	public void closeView() {
		if(this.view.getStage() != null) {			
			this.view.closeStage();
		}
	}
}

