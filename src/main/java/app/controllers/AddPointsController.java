package app.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import app.components.AlertFactory;
import app.models.AbstractModel;
import app.models.datas.WorkingDataset;
import app.models.datas.data.DataType;
import app.utils.LoggerUtils;
import app.utils.ProjectUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * @TODO : Change this to modal view
 */
public class AddPointsController extends AbstractController {
    @FXML protected Label dataTypeLabel;
    @FXML protected Label formatLabel;
    @FXML protected TextArea textAreaPoints;
    protected File dynamicFile;
    protected DataType dataType;

    @FXML
    @Override
    public void initialize() {
    }
    
    @Override
    public void setModel(AbstractModel model) {
    	super.setModel(model);
    	this.updateInfos();
    }
    
    private void setupFile() {
    	this.dataType = DataType.PASSENGER;
		if(dataTypeLabel.getText().equalsIgnoreCase("irisdata")) {
			this.dataType = DataType.IRIS;
		}
		this.dynamicFile = ProjectUtils.getFile(String.format("/data/dynamic%s.csv", this.dataType.getCsvName()));
	}

	public WorkingDataset<?> getWorkingDataset() {
    	return (WorkingDataset<?>) this.model;
    }
    
    private void updateInfos() {
    	String dataType = this.getWorkingDataset().getData().get(0).getClass().getSimpleName();
		this.dataTypeLabel.setText(dataType);
		this.setupFile();
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(dynamicFile));
			if(bReader.ready()) {
				this.formatLabel.setText(bReader.readLine());
			}
			bReader.close();
		} catch (Exception e) {
			LoggerUtils.exception(e);
		}
		
	}

	@FXML public void addPointsClicked() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(dynamicFile));
			String save = bReader.ready() ? bReader.readLine() : "";
			bReader.close();
			BufferedWriter appendBWriter = new BufferedWriter(new FileWriter(dynamicFile, true));
			appendBWriter.append("\n"+textAreaPoints.getText());
			appendBWriter.close();
			this.getWorkingDataset().addDataFromCsv(this.dataType, this.dynamicFile);
			FileWriter removeWriter = new FileWriter(dynamicFile);
			removeWriter.write(save);
			removeWriter.close();
			AlertFactory.createSuccessAlert("Points have been added.\nPress classify to see them appear classified.").show();
			this.closeView();
		} catch (Exception e) {
			LoggerUtils.exception(e);
		}
    }

    
}