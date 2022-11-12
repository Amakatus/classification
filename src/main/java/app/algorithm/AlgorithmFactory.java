package app.algorithm;

import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;

public interface AlgorithmFactory {
    public static <T extends Data> void createAlgorithm(WorkingDataset<T> workingDataset, int k) {
        workingDataset.addAlgorithm(new KNNAlgorithm<>(workingDataset, k));
    }
}
