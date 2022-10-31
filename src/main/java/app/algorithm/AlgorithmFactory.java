package app.algorithm;

import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;

public abstract class AlgorithmFactory {
	public static <T extends Data> void createAlgorithm(WorkingDataset<T> workingDataset, int k) {
		workingDataset.addAlgorithm(new KNNAlgorithm<T>(workingDataset, k));
	}
}
