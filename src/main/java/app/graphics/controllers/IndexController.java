package app.graphics.controllers;

import app.App;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.views.View;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class IndexController extends AbstractController {
	private int numberTabs = 1;
	
	@FXML protected MFXButton newTabButton;
	@FXML protected TabPane tabPane;
	@FXML protected VBox leftBox;
	protected TreeView<Object> treeView;

	@Override
	public void initialize() {
		App app = App.getInstance();
		this.setupTreeView();
	}

	private void setupTreeView() {
		TreeItem<Object> rootItem = new TreeItem<>(new ReferenceDataset<AbstractData>("Datasets"));
		rootItem.setExpanded(true);
		this.treeView = new TreeView<>();
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if(event.getClickCount() == 2){
				this.handleDoubleClickEvent(event);
			}
		});
		treeView.setMinHeight(this.leftBox.getPrefHeight());
		treeView.setRoot(rootItem);
		this.leftBox.getChildren().add(treeView);
	}

	private void handleDoubleClickEvent(MouseEvent event) {
		Object selectedItem = treeView.getSelectionModel().getSelectedItem().getValue();
		if(selectedItem == null) return;
		if(selectedItem.getClass().isAssignableFrom(WorkingDataset.class)){
			System.out.println("CLICKED A WORKING DATASET !!!");
			// Show customisation of working dataset view
		} else if(selectedItem.getClass().isAssignableFrom(KNNAlgorithm.class)){
			System.out.println("CLICKED A KNN ALGO !!!");
			// Create a new tab to show the working datas of its working dataset parent .
		}
	}

	public void addWorkingDataset(WorkingDataset<? extends AbstractData> dataset){
		TreeItem<Object> rootItem = treeView.getRoot();
		TreeItem<Object> childItem = new TreeItem<>(dataset);
		rootItem.getChildren().add(childItem);
	}

	@FXML
	private void exitApp() { Platform.exit(); }

	@FXML
	private void createNewTab(KNNAlgorithm<?> algo) {
		String title = algo.getWorkingDataset().getTitle()+algo.getKNeighbours();
		ObservableList<Tab> tabs = tabPane.getTabs();
		for(Tab tab : tabs) {
			if(tab.getText().equalsIgnoreCase(title)) {
				this.tabPane.getSelectionModel().select(tab);
				return;
			}
		}
		View scatterChartView = new View("scatterChartView");
		Tab newTab = new Tab(title, scatterChartView.getLoadedResource());
		ScatterChartController dsController = (ScatterChartController) scatterChartView.getController();
		dsController.setTitle(newTab.getText());
		this.tabPane.getTabs().add(newTab);
		this.tabPane.getSelectionModel().select(newTab);
		this.numberTabs++;
	}

	@FXML
	public void newDataset(ActionEvent actionEvent) {
		View datasetCreatorView = new View("createWorkingdataset", "Dataset creator");
		DatasetCreatorController controller = (DatasetCreatorController) datasetCreatorView.getController();
		controller.setIndexController(this);
		datasetCreatorView.show();
	}
}