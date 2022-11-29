package app.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import app.exceptions.FieldNotNumberException;
import app.models.AbstractModel;
import app.models.algorithm.KNNAlgorithm;
import app.models.datas.WorkingDataset;
import app.utils.ClassUtils;
import app.utils.LoggerUtils;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class ScatterChartController extends AbstractController {
    @FXML
    protected MFXCheckbox normalizeCheckbox;
    @FXML
    protected MFXComboBox<String> axisXSelector;
    @FXML
    protected MFXComboBox<String> axisYSelector;
    @FXML
    protected ScatterChart<Number, Number> scatterChart;
    @FXML
    protected Label datasetTitle;
    @FXML protected Label classifierName;
    @FXML protected MFXListView<String> fieldListView;
    protected WorkingDataset<?> workingDataset;
    protected String xLabelField;
    protected String yLabelField;

    private KNNAlgorithm<?> getAlgorithm() {
        return (KNNAlgorithm<?>) this.model;
    }

    private boolean showReferencesDatas = false;
    private boolean valueNormalized;

    @Override
    public void setModel(AbstractModel model) {
        this.model = model;
        this.workingDataset = this.getAlgorithm().getWorkingDataset();
        this.setTitle(String.format("%s%n%s points classified on %s", this.workingDataset.getTitle(), this.workingDataset.getData().size(), this.workingDataset.getCategoryField()));
        this.initScatterChart();
    }

    public void initScatterChart() {
        this.registerAxisFieldsNames();
        this.setDefaultAxis();
        this.handleSelectorsChanges();
        this.classifyDatasButtonClicked();
        this.initNormalized();
        this.initExtraInfos();
    }

    private void initExtraInfos() {
		this.classifierName.setText(this.getAlgorithm().getCalculator().simpleName());
		ObservableList<String> fieldsList = FXCollections.observableArrayList();
		fieldsList.add("Category : "+this.workingDataset.getCategoryField());
		for(String field : this.workingDataset.getDistanceFields()) {
			fieldsList.add("Distance : " + field);
		}
		this.fieldListView.setItems(fieldsList);
	}

	private void initNormalized() {
        this.valueNormalized = this.workingDataset.isNormalized();
        if (this.valueNormalized) this.normalizeCheckbox.setSelected(true);
    }

    private void registerAxisFieldsNames() {
        ObservableList<String> fieldsNames = FXCollections.observableArrayList();
        List<Field> fields = ClassUtils.getNumberFields(this.workingDataset.getData().get(0));
        fields.forEach(field -> fieldsNames.add(field.getName()));
        this.axisXSelector.setItems(fieldsNames);
        this.axisYSelector.setItems(fieldsNames);
    }

    private void setDefaultAxis() {
        this.xLabelField = this.axisYSelector.getItems().get(0);
        this.axisXSelector.selectItem(xLabelField);
        this.yLabelField = this.axisYSelector.getItems().get(1);
        this.axisYSelector.selectItem(yLabelField);
    }

    private void handleSelectorsChanges() {
        this.axisXSelector.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.equals(yLabelField)) {
                this.xLabelField = newVal;
                this.scatterChart.getXAxis().setLabel(newVal);
                this.showDatas();
            }
        });

        this.axisYSelector.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.equals(xLabelField)) {
                this.yLabelField = newVal;
                this.scatterChart.getYAxis().setLabel(newVal);
                this.showDatas();
            }
        });
    }

    public void setTitle(String title) {
        this.datasetTitle.setText(title);
    }

    public void showDatas() {
        this.getAlgorithm().classifyWorkingDataset();
        if (showReferencesDatas)
            this.addDatas(this.workingDataset.getBothDataByCategories());
        else
            this.addDatas(this.workingDataset.getWorkingDataByCategories());
    }

    public void addDatas(Map<String, ? extends List<?>> dataByCategories) {
        this.scatterChart.setData(FXCollections.observableArrayList());
        dataByCategories.forEach((categoryName, dataList) -> {
            XYChart.Series<Number, Number> newSerie = new XYChart.Series<>();
            newSerie.setName(categoryName);
            dataList.forEach(data -> {
                try {
                    double xValue = ClassUtils.getDoubleFromField(data, this.xLabelField);
                    double yValue = ClassUtils.getDoubleFromField(data, this.yLabelField);
                    XYChart.Data<Number, Number> scatterPoint = new XYChart.Data<>(xValue, yValue);
                    newSerie.getData().add(scatterPoint);
                } catch (FieldNotNumberException e) {
                    LoggerUtils.exception(e);
                }
            });
            newSerie.setName(newSerie.getName() + String.format(" (%s)", newSerie.getData().size()));
            this.scatterChart.getData().add(newSerie);
            this.registerDataTooltips(categoryName);
        });
    }

    private void registerDataTooltips(String categoryName) {
        String categoryFieldName = this.workingDataset.getCategoryField();
        this.scatterChart.getData().forEach(series -> series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%s : %s%n%s : %s%n%s : %s", categoryFieldName, categoryName, xLabelField, data.getXValue(), yLabelField, data.getYValue()));
            Tooltip.install(data.getNode(), tooltip);
        }));
    }

    public void classifyDatasButtonClicked() {
        this.getAlgorithm().classifyWorkingDataset();
        this.showDatas();
    }

    @FXML
    public void toggleDisplayReferences() {
        this.showReferencesDatas = !this.showReferencesDatas;
        this.showDatas();
    }

    public void toggleNormalizeDatas() {
        this.valueNormalized = !this.valueNormalized;
        if (valueNormalized)
            this.workingDataset.normalizeDatas();
        else
            this.workingDataset.unNormalizeDatas();
        this.showDatas();
    }

}