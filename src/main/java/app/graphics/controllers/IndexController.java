package app.graphics.controllers;

import app.App;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.Observer;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.views.View;
import app.utils.ProjectUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class IndexController extends AbstractController implements Observer {
	private int numberTabs = 1;
	
	@FXML protected MFXButton newTabButton;
	@FXML protected TabPane tabPane;
	@FXML protected VBox leftBox;
	protected TreeView<Object> treeView;

	@Override
	public void initialize() {
		this.setupTreeView();
		App.getInstance().attach(this);
		WorkingDataset<IrisData> testWDS = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
		App.getInstance().addWorkingDataset(testWDS);
	}

	private void setupTreeView() {
		TreeItem<Object> rootItem = new TreeItem<>(new ReferenceDataset<AbstractData>("Datasets"));
		rootItem.setExpanded(true);
		this.treeView = new TreeView<>();
		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if(event.getClickCount() == 2){
				this.handleDoubleClickEvent();
			}
		});
		treeView.setMinHeight(this.leftBox.getPrefHeight());
		treeView.setRoot(rootItem);
		this.leftBox.getChildren().add(treeView);
	}

	private void handleDoubleClickEvent() {
		Object selectedItem = treeView.getSelectionModel().getSelectedItem().getValue();
		if(selectedItem == null) return;
		if(selectedItem.getClass().isAssignableFrom(WorkingDataset.class)){
			System.out.println("CLICKED A WORKING DATASET !!!");
		} else if(selectedItem.getClass().isAssignableFrom(KNNAlgorithm.class)){
			System.out.println("CLICKED A KNN ALGO !!!");
			this.createNewTab((KNNAlgorithm<?>) selectedItem);
			// Create a new tab to show the working datas of its working dataset parent .
		}
	}

	public void addWorkingDataset(WorkingDataset<? extends AbstractData> dataset){
		TreeItem<Object> rootItem = treeView.getRoot();
		TreeItem<Object> childItem = new TreeItem<>(dataset);
		rootItem.getChildren().add(childItem);
	}

	private TreeItem<Object> getTreeForDataset(WorkingDataset<? extends AbstractData> dataset){
		ObservableList<TreeItem<Object>> datasets = treeView.getRoot().getChildren();
		for(TreeItem<Object> treeDataset : datasets){
			if(treeDataset.getValue().equals(dataset)) {
				return treeDataset;
			}
		}
		return null;
	}

	public void addAlgorithm(WorkingDataset<? extends AbstractData> dataset, KNNAlgorithm<? extends AbstractData> algorithm){
		TreeItem<Object> datasetItem = this.getTreeForDataset(dataset);
		if(datasetItem != null)
			datasetItem.getChildren().add(new TreeItem<>(algorithm));
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
	public void newDatasetButtonClicked() {
		View datasetCreatorView = new View("createWorkingdataset", "Dataset creator");
		datasetCreatorView.show();
	}

	public void newAlgorithmButtonClicked() {
		View algorithmCreatorView = new View("createAlgorithm", "Algorithm creator");
		algorithmCreatorView.show();
	}

	@Override
	public void sendUpdate() {

	}

	@Override
	public void sendUpdate(Object object) {
		if(object.getClass().isAssignableFrom(WorkingDataset.class)){
			WorkingDataset<?> workingDataset = (WorkingDataset<? extends AbstractData>) object;
			this.addWorkingDataset(workingDataset);
			workingDataset.attach(this);
		} else if(object.getClass().isAssignableFrom(KNNAlgorithm.class)){
			KNNAlgorithm<?> algorithm = (KNNAlgorithm<?>) object;
			this.addAlgorithm(algorithm.getWorkingDataset(), algorithm);
		}
	}
}