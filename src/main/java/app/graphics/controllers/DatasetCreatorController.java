package app.graphics.controllers;

import app.graphics.components.AlertFactory;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.models.datas.data.DataType;
import app.utils.Logger;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;

/**
 * @TODO : Change this to modal view
 */
public class DatasetCreatorController extends AbstractController {
    @FXML
    protected HBox distanceWrapper;
    @FXML
    protected HBox categoryWrapper;
    @FXML
    protected Label distanceInputInfo;
    @FXML
    protected Label fileLabel;
    @FXML
    protected MFXComboBox<DataType> inputType;
    @FXML
    protected MFXTextField inputName;
    @FXML
    protected MFXComboBox<String> inputCategory;
    @FXML
    protected MFXComboBox<String> inputDistance;
    @FXML
    protected MFXButton selectButton;

    protected ObservableList<String> typeFields = FXCollections.observableArrayList();
    protected IndexController indexController;
    protected FileChooser fileChooser;
    protected File fileToClassify;

    public void setIndexController(IndexController controller) {
        this.indexController = controller;
        this.fileChooser = new FileChooser();
        this.fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
    }

    @FXML
    public void initialize() {
        this.initTypes();
        this.initFields();
        this.distanceWrapper.setVisible(false);
        this.categoryWrapper.setVisible(false);
        this.distanceInputInfo.setVisible(false);
    }

    private void initTypes() {
        // @TODO : Only show available category fields for the category dropdown.
        ObservableList<DataType> typeList = FXCollections.observableArrayList();
        Collections.addAll(typeList, DataType.values());
        this.inputType.setItems(typeList);
        this.inputType.valueProperty().addListener((obs, oldVal, newVal) -> {
            resetComboDefaultValues();
            this.changeFields(newVal);
        });
    }

    private void resetComboDefaultValues() {
        this.distanceWrapper.setVisible(true);
        this.categoryWrapper.setVisible(true);
        this.distanceInputInfo.setVisible(true);
        this.inputCategory.getSelectionModel().clearSelection();
        this.inputDistance.getSelectionModel().clearSelection();
    }

    private void initFields() {
        this.inputCategory.setItems(this.typeFields);
        this.inputDistance.setItems(this.typeFields);
    }

    private void changeFields(DataType dataType) {
        try {
            Class<? extends AbstractData> dataClass = dataType.getTypeClass();
            Field[] fields = dataClass.getDeclaredFields();
            this.typeFields.clear();
            for (Field field : fields) {
                this.typeFields.add(field.getName());
            }
        } catch (Exception e) {
            Logger.exception(e);
        }
    }

    @FXML
    public void createDataset(MouseEvent mouseEvent) {
        if (!this.formIsComplete()) {
            Alert alert = AlertFactory.createErrorAlert("Please, complete the form before submitting.");
            alert.show();
            return;
        }
        WorkingDataset<? extends AbstractData> newDataset = DatasetFactory.createWorkingDataset(this.inputName.getText(), inputType.getValue(), fileToClassify);
        newDataset.setCategoryField(inputCategory.getValue());
        newDataset.addDistanceFieldString(inputDistance.getValue());
        this.indexController.addWorkingDataset(newDataset);
        this.closeView();
    }

    private boolean formIsComplete() {
        return !this.inputName.getText().isEmpty() && this.inputType.getValue() != null && this.inputDistance.getValue() != null
				&& this.inputCategory.getValue() != null && this.fileToClassify != null;
    }

    public void openDialogFile(MouseEvent mouseEvent) {
        File selectedFile = this.fileChooser.showOpenDialog(this.view.getStage());
        if (selectedFile != null) {
            this.fileLabel.setText(selectedFile.getName());
            this.fileToClassify = selectedFile;
        }
    }
}