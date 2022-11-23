package app.controllers;

import app.models.AbstractModel;
import app.models.Observer;
import app.views.View;
import app.utils.LoggerUtils;

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

    @Override
    public void initialize() {
        // override this method to make operations during FXML init.
    }

    public void sendUpdate() {
        LoggerUtils.log("Controller " + this.getClass().getSimpleName() + " has received update but haven't done anything.");
    }

    public void sendUpdate(Object object) {
        LoggerUtils.log("Controller " + this.getClass().getSimpleName() + " has received update but haven't done anything.");
    }

    public void setModel(AbstractModel model) {
        this.model = model;
        model.attach(this);
    }

    public AbstractModel getModel() {
        return this.model;
    }

    public void closeView() {
        if (this.view.getStage() != null) {
            this.view.closeStage();
        }
    }
}

