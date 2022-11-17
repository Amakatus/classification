package app;

import app.graphics.models.datas.ReferenceDatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;

import java.util.ArrayList;
import java.util.List;

public class App {
	// Singleton
	private static App instance = null;
	
	public static App getInstance() {
		if(App.instance == null)
			App.instance = new App();
		return App.instance;
	}
	
	// Classe
	protected List<WorkingDataset<? extends Data>> workingDatasets;
	protected List<ReferenceDataset<? extends Data>> referenceDatasets;
	
	private App() {
		this.workingDatasets = new ArrayList<>();
		this.referenceDatasets = new ArrayList<>();
		this.loadReferenceDatasets();
	}
	
	public List<WorkingDataset<? extends Data>> getWorkingDatasets(){
		return this.workingDatasets;
	}
	
	public void addWorkingDataset(WorkingDataset<? extends Data> dataset) {
		this.workingDatasets.add(dataset);
	}
	
	public void addWorkingDataset(WorkingDataset<?>... datasets) {
		for(WorkingDataset<?> dataset : datasets) {
			this.addWorkingDataset(dataset);
		}
	}
	
	public List<ReferenceDataset<? extends Data>> getReferenceDatasets() {
		return this.referenceDatasets;
	}
	
	public void addReferenceDataset(ReferenceDataset<? extends Data> dataset) {
		this.referenceDatasets.add(dataset);
	}
	
	public void clearWorkingDatasets() {
		this.workingDatasets.clear();
	}
	
	public void clearReferenceDatasets() {
		this.referenceDatasets.clear();
	}
	
	// Only for dev purpose, should be modified soon.
	public void loadReferenceDatasets() {
		this.addReferenceDataset(ReferenceDatasetFactory.irisReferenceDataset("IrisRef"));
		this.addReferenceDataset(ReferenceDatasetFactory.titanicPassengerReferenceDataset("TitanicRef"));
	}
}