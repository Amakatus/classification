package app;

import java.util.ArrayList;
import java.util.List;

import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.IrisData;
import app.utils.CSVUtils;

public class App {
	// Singleton
	private static App instance = null;
	
	public static App getInstance() {
		if(App.instance == null)
			App.instance = new App();
		return App.instance;
	}
	
	// Classe
	protected List<Dataset<? extends Data>> workingDatasets;
	protected List<Dataset<? extends Data>> referenceDatasets;
	
	private App() {
		this.workingDatasets = new ArrayList<>();
		this.referenceDatasets = new ArrayList<>();
	}
	
	public List<Dataset<? extends Data>> getWorkingDatasets(){
		return this.workingDatasets;
	}
	
	public void addWorkingDataset(Dataset<? extends Data> dataset) {
		this.workingDatasets.add(dataset);
	}
	
	public List<Dataset<? extends Data>> getReferenceDatasets() {
		return this.referenceDatasets;
	}
	
	public void addReferenceDataset(Dataset<? extends Data> dataset) {
		this.referenceDatasets.add(dataset);
	}
	
	public void clearWorkingDatasets() {
		this.workingDatasets.clear();
	}
	
	public void clearReferenceDatasets() {
		this.referenceDatasets.clear();
	}
	
	public void loadReferenceDatasets() {
		this.addReferenceDataset(new ReferenceDataset<IrisData>("IrisReferenceDataset", CSVUtils.loadIrisCSV()));
	}
}