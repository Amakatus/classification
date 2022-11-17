package app;

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
		/*ReferenceDataset<IrisData> rDS = DatasetFactory.irisReferenceDataset("rDSTest");
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
		this.addWorkingDataset(wDS, woDS, wDSTwo);*/
	}
}