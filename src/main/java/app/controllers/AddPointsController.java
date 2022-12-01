package app.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    @FXML Label dataTypeLabel;
    @FXML TextArea textAreaPoints;

    @FXML
    @Override
    public void initialize() {
        System.out.println("good");
    }
    
    @Override
    public void setModel(AbstractModel model) {
    	super.setModel(model);
    	this.updateInfos();
    }
    
    public WorkingDataset<?> getWorkingDataset() {
    	return (WorkingDataset<?>) this.model;
    }
    
    private void updateInfos() {
    	String dataType = this.getWorkingDataset().getData().get(0).getClass().getSimpleName();
		this.dataTypeLabel.setText(dataType);
		this.textAreaPoints.setPromptText("test");
	}

	@FXML void addPointsClicked() {
		String csvName = DataType.PASSENGER.getCsvName();
		if(dataTypeLabel.getText().equalsIgnoreCase("irisdata")) {
			csvName = DataType.IRIS.getCsvName();
		}
		File dynamicFile = ProjectUtils.getFile(String.format("/data/dynamic%s.csv", csvName));
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(dynamicFile, true));
			bWriter.append(textAreaPoints.getText());
			bWriter.close();
			BufferedReader bReader = new BufferedReader(new FileReader(dynamicFile));
			while(bReader.ready()) {
				System.out.println(bReader.readLine());
			}
			bReader.close();
		} catch (IOException e) {
			LoggerUtils.exception(e);
		}
    }

    
}