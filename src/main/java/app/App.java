package app;

import java.util.ArrayList;
import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.datas.data.Data;
import app.graphics.models.Dataset;

public class App {
	// Singleton
	private static App instance = null;
	
	public static App getInstance() {
		if(App.instance == null)
			App.instance = new App();
		return App.instance;
	}
	
	// Classe
	protected List<Dataset<? extends Data>> dataset;
	
	private App() {
		this.dataset = new ArrayList<>();
		this.initTestsDatasets();
	}
	
	public List<Dataset<? extends Data>> getDatasets(){
		return this.dataset;
	}
	
	public void addDataset(Dataset<? extends Data> dataset) {
		this.dataset.add(dataset);
	}

	private void initTestsDatasets() {
		Dataset<?> d1 = new Dataset<>("Iris");
		Dataset<?> d2 = new Dataset<>("TitanicPassengers");
		
		new KNNAlgorithm<>(d1, 2);
		new KNNAlgorithm<>(d1, 3);
		new KNNAlgorithm<>(d1, 4);
		new KNNAlgorithm<>(d1, 5);
		new KNNAlgorithm<>(d2, 4);
		new KNNAlgorithm<>(d2, 5);
		new KNNAlgorithm<>(d2, 6);
		
		this.dataset.add(d1);
		this.dataset.add(d2);
	}
}