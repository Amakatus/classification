package app.controllers;

import app.App;
import app.components.AlertFactory;
import app.models.datas.DatasetFactory;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;
import app.models.datas.data.DataType;
import app.utils.ClassUtils;
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
import org.controlsfx.control.CheckComboBox;

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
    protected Label fileLabel;
    @FXML
    protected MFXComboBox<DataType> inputType;
    @FXML
    protected MFXTextField inputName;
    @FXML
    protected MFXComboBox<String> inputCategory;
    @FXML
    protected CheckComboBox<String> inputDistance;
    @FXML
    protected MFXButton selectButton;

    protected ObservableList<String> categoryFields = FXCollections.observableArrayList();
    protected FileChooser fileChooser;
    protected File fileToClassify;

    @FXML
    @Override
    public void initialize() {
        this.fileChooser = new FileChooser();
        this.fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        this.initTypes();
        this.initFields();
        this.distanceWrapper.setVisible(false);
        this.categoryWrapper.setVisible(false);
    }

    private void initTypes() {
        // @TODO : Only show available category fields for the category dropdown.
        ObservableList<DataType> typeList = FXCollections.observableArrayList();
        Collections.addAll(typeList, DataType.values());
        this.inputType.setItems(typeList);
        this.inputType.valueProperty().addListener((obs, oldVal, newVal) -> {
            this.resetComboDefaultValues();
            this.changeFields(newVal);
        });
    }

    private void resetComboDefaultValues() {
        this.distanceWrapper.setVisible(true);
        this.categoryWrapper.setVisible(true);
        this.inputCategory.getSelectionModel().clearSelection();
        this.inputDistance.getCheckModel().clearChecks();
        this.inputDistance.getItems().clear();
    }

    private void initFields() {
        this.inputCategory.setItems(this.categoryFields);
        this.inputDistance.setStyle(this.inputCategory.getStyle());
    }

    private void changeFields(DataType dataType) {
        Field[] fields = dataType.getFields();
        this.categoryFields.clear();
        ObservableList<String> inputDistanceItems = this.inputDistance.getItems();
        inputDistanceItems.clear();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (ClassUtils.canBeCategoryField(field)) {
                this.categoryFields.add(fieldName);
            }
            inputDistanceItems.add(fieldName);
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
        inputDistance.getCheckModel().getCheckedItems().forEach(newDataset::addDistanceFieldString);
        App.getInstance().addWorkingDataset(newDataset);
        this.closeView();
    }

    private boolean formIsComplete() {
        return !this.inputName.getText().isEmpty() && this.inputType.getValue() != null && !this.inputDistance.getCheckModel().isEmpty()
                && this.inputCategory.getValue() != null && this.fileToClassify != null;
    }

    public void openDialogFile() {
        File selectedFile = this.fileChooser.showOpenDialog(this.view.getStage());
        if (selectedFile != null) {
            this.fileLabel.setText(selectedFile.getName());
            this.fileToClassify = selectedFile;
        }
    }
}