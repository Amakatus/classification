package app.algorithm;

import app.graphics.models.DatasetType;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.data.Data;

public abstract class AlgorithmFactory {
	public static <T extends Data> boolean createAlgorithm(Dataset<T> workingDataset, Dataset<T> referenceDataset, int k) {
		if (workingDataset.getType() != DatasetType.WORKING || referenceDataset.getType() != DatasetType.REFERENCE)
			return false;
		workingDataset.addAlgorithm(new KNNAlgorithm<T>(workingDataset, referenceDataset, k));
		return true;
	}
}
