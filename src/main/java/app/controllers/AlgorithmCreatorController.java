package app.controllers;

import app.App;
import app.components.AlertFactory;
import app.models.algorithm.KNNAlgorithm;
import app.models.algorithm.calculators.CalculatorFactory;
import app.models.algorithm.geometry.GeometryFactory;
import app.models.algorithm.geometry.IGeometry;
import app.models.datas.WorkingDataset;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * @TODO : Change this to modal view
 */
public class AlgorithmCreatorController extends AbstractController {
    @FXML protected MFXComboBox<String> geometryComboBox;
    @FXML protected MFXComboBox<String> classifierComboBox;
    @FXML
    protected MFXSlider neighboursSlider;
    @FXML
    protected MFXComboBox<WorkingDataset<?>> datasetComboBox;
    protected ObservableList<WorkingDataset<?>> workingDatasets = FXCollections.observableArrayList();

    @FXML
    @Override
    public void initialize() {
        this.setupDatasets();
        this.setupComboBox();
        this.datasetComboBox.setItems(this.workingDatasets);
    }

    private void setupComboBox() {
        this.geometryComboBox.getItems().setAll("Euclidean", "Manhattan");
        this.geometryComboBox.selectItem("Euclidean");
        this.classifierComboBox.getItems().setAll("Distance", "Random");
        this.classifierComboBox.selectItem("Distance");
    }

    private void setupDatasets() {
        this.workingDatasets.addAll(App.getInstance().getWorkingDatasets());
    }

    public void createAlgorithmButtonClicked() {
        if (!this.formIsComplete()) {
            Alert alert = AlertFactory.createErrorAlert("Please, complete the form before submitting.");
            alert.show();
            return;
        }
        WorkingDataset<?> workingDataset = this.datasetComboBox.getSelectionModel().getSelectedItem();
        KNNAlgorithm<?> algo = workingDataset.createKNN((int) this.neighboursSlider.getValue(), true, GeometryFactory.createGeometryAlgorithm(geometryComboBox.getSelectedItem(), workingDataset.getDistanceFields()));
        algo.setCalculator(classifierComboBox.getSelectedItem());
        this.closeView();
    }

    private boolean formIsComplete() {
        return this.datasetComboBox.getSelectionModel().getSelectedItem() != null;
    }
}