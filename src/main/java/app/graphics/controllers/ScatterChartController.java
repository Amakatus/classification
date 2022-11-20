package app.graphics.controllers;

import app.algorithm.KNNAlgorithm;
import app.exceptions.FieldNotNumberException;
import app.graphics.models.datas.WorkingDataset;
import app.utils.ClassUtils;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ScatterChartController extends AbstractController {
	public MFXComboBox<String> axisXSelector;
	public MFXComboBox<String> axisYSelector;
	@FXML protected ScatterChart<Number,Number> scatterChart;
	@FXML protected Label datasetTitle;
	protected WorkingDataset<?> workingDataset;
	protected KNNAlgorithm<?> algorithm;
	protected String xLabelField;
	protected String yLabelField;

	@FXML
    public void initialize() {

    }

	public void setAlgorithm(KNNAlgorithm<?> algorithm) {
		this.algorithm = algorithm;
		this.workingDataset = algorithm.getWorkingDataset();
	}

	public void initAxisSelectors() {
		ObservableList<String> fieldsNames = FXCollections.observableArrayList();
		List<Field> fields = ClassUtils.getNumberFields(this.workingDataset.getDatas().get(0));
		fields.forEach(field -> fieldsNames.add(field.getName()));
		this.axisXSelector.setItems(fieldsNames);
		this.axisYSelector.setItems(fieldsNames);
		this.yLabelField = this.axisYSelector.getItems().get(0);
		this.xLabelField = this.axisYSelector.getItems().get(1);
		this.handleSelectorsChanges();
	}

	private void handleSelectorsChanges() {
		this.axisXSelector.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(!newVal.equals(yLabelField)) {
				this.xLabelField = newVal;
				this.addDatas();
			}
		});

		this.axisYSelector.valueProperty().addListener((obs, oldVal, newVal) -> {
			if(!newVal.equals(xLabelField)){
				this.yLabelField = newVal;
				this.addDatas();
			}
		});
	}

	public void setTitle(String title) {
		this.datasetTitle.setText(title);
	}

	public void setWorkingDataset(WorkingDataset<?> workingDataset){
		this.workingDataset = workingDataset;
	}

	public void addDatas() {
		this.scatterChart.getData().clear();
		Map<String, ? extends List<?>> dataByCategories = this.workingDataset.getDataByCategories();
		dataByCategories.forEach((categoryName, dataList) -> {
			XYChart.Series<Number, Number> newSerie = new XYChart.Series<>();
			newSerie.setName(categoryName);
			dataList.forEach(data -> {
				try {
					XYChart.Data<Number,Number> scatterPoint = new XYChart.Data<>(ClassUtils.getDoubleFromField(data, this.xLabelField),ClassUtils.getDoubleFromField(data, this.yLabelField));
					newSerie.getData().add(scatterPoint);
				} catch (FieldNotNumberException e) {
					e.printStackTrace();
				}
			});
			this.scatterChart.getData().add(newSerie);
			this.scatterChart.getData().forEach(series -> series.getData().forEach(data -> {
				Tooltip tooltip = new Tooltip();
				tooltip.setText(data.getYValue().toString());
				Tooltip.install(data.getNode(), tooltip);
			}));
		});
	}

	public void classifyDatasButtonClicked() {
		this.algorithm.classifyWorkingDataset();
		this.addDatas();
	}
}