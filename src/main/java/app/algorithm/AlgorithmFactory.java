package app.algorithm;

import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;

public interface AlgorithmFactory {
    static <T extends AbstractData> void createKNN(WorkingDataset<T> workingDataset, int k) {
        workingDataset.addAlgorithm(new KNNAlgorithm<>(workingDataset, k));
    }
}
