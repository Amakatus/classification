package app.models.algorithm.calculators;

import app.models.algorithm.Algorithm;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.data.AbstractData;

import java.util.HashMap;
import java.util.Map;

public class RandomDistanceCalculator<T extends AbstractData> extends AbstractCalculator<T> {
    protected RandomDistanceCalculator(Algorithm<T> algorithm, IGeometryCalculator<T> geometry) {
        super(algorithm, geometry);
    }

    @Override
    public Map<T, Double> getDistances(T workingData) {
        Map<T, Double> dataDistancesMap = new HashMap<>();
        dataDistancesMap.put(workingData, -1.); // 600 IQ
        for (T refData : this.getReferenceDataset().getData()) {
            dataDistancesMap.put(refData, Math.random());
        }
        return dataDistancesMap;
    }
}
