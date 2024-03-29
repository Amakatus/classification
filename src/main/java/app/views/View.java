package app.views;


import app.controllers.AbstractController;
import app.utils.LoggerUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class View {

    protected Stage stage;
    protected AbstractController controller;
    protected Parent loadedResource;

    public View(String viewName, String windowName, int width, int height) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + viewName + ".fxml"));
        try {
            this.loadedResource = loader.load();
            if (this.setupController(loader)) {
                setupStage(windowName, width, height, loadedResource);
            }
        } catch (IOException e) {
            LoggerUtils.exception(e);
        }
    }

    private void setupStage(String windowName, int width, int height, Parent root) {
        this.stage = new Stage();
        this.stage.setTitle(windowName);
        if (width > 0 && height > 0) {
            this.stage.setScene(new Scene(root, width, height));
        } else {
            this.stage.setScene(new Scene(root));
        }
        this.stage.setResizable(false);
    }

    private boolean setupController(FXMLLoader loader) {
        AbstractController loaderController = loader.getController();
        if (loaderController == null) return false;
        this.controller = loaderController;
        this.controller.setView(this);
        return true;
    }

    public View(String viewPath, String windowName) {
        this(viewPath, windowName, 0, 0);
    }

    public View(String viewPath) {
        this(viewPath, viewPath);
    }

    public Stage getStage() {
        return this.stage;
    }

    public AbstractController getController() {
        return this.controller;
    }

    public Parent getLoadedResource() {
        return this.loadedResource;
    }

    public void show() {
        this.stage.show();
    }

    public void closeStage() {
        this.stage.close();
    }
}

