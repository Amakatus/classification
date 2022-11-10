package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.IrisData;

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
		this.loadReferenceDatasets();
	}
	
	public List<Dataset<? extends Data>> getWorkingDatasets(){
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
	
	public List<Dataset<? extends Data>> getReferenceDatasets() {
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
		ReferenceDataset<IrisData> rDS = DatasetFactory.irisReferenceDataset("rDSTest");
		this.addReferenceDataset(rDS);
		WorkingDataset<IrisData> wDS = new WorkingDataset<>("Iris", Arrays.asList(new IrisData()), rDS);
		wDS.createAlgorithm(5);
		wDS.createAlgorithm(3);
		wDS.createAlgorithm(2);
		WorkingDataset<IrisData> woDS = new WorkingDataset<>("OtherIris", Arrays.asList(new IrisData()), rDS);
		woDS.createAlgorithm(6);
		woDS.createAlgorithm(4);
		WorkingDataset<IrisData> wDSTwo = new WorkingDataset<>("TitanicPassengers", Arrays.asList(new IrisData()), rDS);
		wDSTwo.createAlgorithm(1);
		wDSTwo.createAlgorithm(4);
		this.addWorkingDataset(wDS, woDS, wDSTwo);
	}
}