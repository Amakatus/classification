package app.models.algorithm;

import app.models.algorithm.calculators.ICalculator;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public interface AlgorithmFactory {
    static <T extends AbstractData> void createKNN(WorkingDataset<T> workingDataset, int k, boolean autoClassify, ICalculator<T> calculator) {
        workingDataset.addAlgorithm(new KNNAlgorithm<>(workingDataset, k, autoClassify, calculator));
    }
}
