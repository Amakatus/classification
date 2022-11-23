package app.models.algorithm;

import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public interface AlgorithmFactory {
    static <T extends AbstractData> void createKNN(WorkingDataset<T> workingDataset, int k, boolean autoClassify) {
        workingDataset.addAlgorithm(new KNNAlgorithm<>(workingDataset, k, autoClassify));
    }

    static <T extends AbstractData> void createKNN(WorkingDataset<T> workingDataset, int k) {
        createKNN(workingDataset, k, false);
    }
}
