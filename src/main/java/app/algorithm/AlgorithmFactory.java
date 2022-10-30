package app.algorithm;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;

public abstract class AlgorithmFactory {
	public static <T extends Data> boolean createAlgorithm(WorkingDataset<T> workingDataset, ReferenceDataset<T> referenceDataset, int k) {
		workingDataset.addAlgorithm(new KNNAlgorithm<T>(workingDataset, referenceDataset, k));
		return true;
	}
}
