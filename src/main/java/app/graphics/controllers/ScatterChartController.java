package app.graphics.controllers;

import app.algorithm.KNNAlgorithm;
import app.exceptions.FieldNotNumberException;
import app.graphics.models.Model;
import app.graphics.models.datas.WorkingDataset;
import app.utils.ClassUtils;
import app.utils.Logger;
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
    protected MFXComboBox<String> axisXSelector;
    protected MFXComboBox<String> axisYSelector;
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

    @Override
    public void setModel(Model model) {
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
                this.addDatas();
            }
        });

        this.axisYSelector.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.equals(xLabelField)) {
                this.yLabelField = newVal;
                this.scatterChart.getYAxis().setLabel(newVal);
                this.addDatas();
            }
        });
    }

    public void setTitle(String title) {
        this.datasetTitle.setText(title);
    }

    public void addDatas() {
        this.scatterChart.getData().clear();
        Map<String, ? extends List<?>> dataByCategories = this.workingDataset.getDataByCategories();
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
                    Logger.exception(e);
                }
            });
            this.scatterChart.getData().add(newSerie);
            this.registerDataTooltips(categoryName);
        });
    }

    private void registerDataTooltips(String categoryName) {
        this.scatterChart.getData().forEach(series -> series.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("Category : %s%n%s : %s%n%s : %s", categoryName, xLabelField, data.getXValue(), yLabelField, data.getYValue()));
            Tooltip.install(data.getNode(), tooltip);
        }));
    }

    public void classifyDatasButtonClicked() {
        this.getAlgorithm().classifyWorkingDataset();
        this.addDatas();
    }
}