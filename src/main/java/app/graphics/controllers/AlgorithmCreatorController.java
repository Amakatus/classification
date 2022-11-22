package app.graphics.controllers;

import app.App;
import app.graphics.components.AlertFactory;
import app.graphics.models.datas.WorkingDataset;
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
    @FXML protected MFXSlider neighboursSlider;
    @FXML protected MFXComboBox<WorkingDataset<?>> datasetComboBox;
    protected ObservableList<WorkingDataset<?>> workingDatasets = FXCollections.observableArrayList();

    @FXML
    @Override
    public void initialize() {
        this.setupDatasets();
        this.datasetComboBox.setItems(this.workingDatasets);
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
        workingDataset.createAlgorithm((int) this.neighboursSlider.getValue(), true);
        this.closeView();
    }

    private boolean formIsComplete() {
        return this.datasetComboBox.getSelectionModel().getSelectedItem() != null;
    }
}