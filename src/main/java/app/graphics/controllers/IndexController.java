package app.graphics.controllers;

import app.App;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;
import app.graphics.views.View;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class IndexController extends Controller {
	int numberTabs = 1;
	
	@FXML MFXButton newTabButton;
	@FXML TabPane tabPane;
	@FXML VBox leftBox;

	@Override
	public void initialize() {
		App app = App.getInstance();
		TreeItem<Object> rootItem = new TreeItem<>(new ReferenceDataset<Data>("Datasets"));
		rootItem.setExpanded(true);
		TreeItem<Object> childItem;
		TreeItem<Object> childOfChild;
		System.err.println("Trying to load datasets " + app.getWorkingDatasets().size());
		for(Dataset<? extends Data> dataset : app.getWorkingDatasets()) {
			childItem = new TreeItem<>(dataset);
			rootItem.getChildren().add(childItem);
			System.out.println("FOUND dataset : " + dataset.getTitle());
			if(dataset instanceof WorkingDataset<?>) {
				WorkingDataset<?> workingDataset = (WorkingDataset<?>) dataset;
				for(KNNAlgorithm<? extends Data> algo : workingDataset.getAlgorithms()) {
					childOfChild = new TreeItem<>(algo);
					childItem.getChildren().add(childOfChild);
				}
			}
		}
		TreeView<Object> treeView = new TreeView<>(rootItem);
		treeView.setRoot(rootItem);
		treeView.setMinHeight(this.leftBox.getPrefHeight());
		this.leftBox.getChildren().add(treeView);	
	}

	@FXML
	void createNewTab(MouseEvent mouseEvent) {
		View newDatasetView = new View("scatterChartView");
		Tab newTab = new Tab("Tab " + this.numberTabs, (Node) newDatasetView.getLoadedResource());
		DatasetController dsController = (DatasetController) newDatasetView.getController();
		dsController.setTitle("Dataset #" + this.numberTabs);
		this.tabPane.getTabs().add(newTab);
		this.numberTabs++;
	}
}