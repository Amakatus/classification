package app.graphics.controllers;

import app.algorithm.KNNAlgorithm;
import app.exceptions.FieldNotNumberException;
import app.graphics.models.AbstractModel;
import app.graphics.models.datas.WorkingDataset;
import app.utils.ClassUtils;
import app.utils.LoggerUtils;
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
    @FXML protected MFXComboBox<String> axisXSelector;
    @FXML protected MFXComboBox<String> axisYSelector;
    @FXML
    protected ScatterChart<Number, Number> scatterChart;
    @FXML
    protected Label datasetTitle;
    protected WorkingDataset<?> workingDataset;
    protected String xLabelField;
    protected String yLabelField;

    private KNNAlgorithm<?> getAlgorithm() {
        return (KNNAlgorithm<?>) this.model;
    }
    private boolean showReferencesDatas = false;

    @Override
    public void setModel(AbstractModel model) {
        this.model = model;
        this.workingDataset = this.getAlgorithm().getWorkingDataset();
        this.setTitle(String.format("%s%n%s points classified on %s", this.workingDataset.getTitle(), this.workingDataset.getDatas().size(), this.workingDataset.getCategoryField()));
        this.initAxisSelectors();
    }

    public void initAxisSelectors() {
        this.registerAxisFieldsNames();
        this.setDefaultAxis();
        this.handleSelectorsChanges();
        this.classifyDatasButtonClicked();
    }

    private void registerAxisFieldsNames() {
        ObservableList<String> fieldsNames = FXCollections.observableArrayList();
        List<Field> fields = ClassUtils.getNumberFields(this.workingDataset.getDatas().get(0));
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
        if(showReferencesDatas)
            this.addDatas(this.workingDataset.getBothDataByCategories());
        else
            this.addDatas(this.workingDataset.getWorkingDataByCategories());
    }

    public void addDatas(Map<String, ? extends List<?>> dataByCategories) {
        this.scatterChart.getData().clear();
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
}