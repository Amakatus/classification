package app.models.algorithm.calculators;

import java.util.HashMap;
import java.util.Map;

import app.models.algorithm.Algorithm;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.data.AbstractData;
import app.models.datas.data.IrisData;
import app.models.datas.data.IrisVariety;

public class RandomDistanceCalculator<T extends AbstractData> extends AbstractCalculator<T> {
    public RandomDistanceCalculator(Algorithm<T> algorithm, IGeometryCalculator<T> geometry) {
        super(algorithm, geometry);
    }

    @Override
    public Map<T, Double> getDistances(T workingData) {
        Map<T, Double> dataDistancesMap = new HashMap<>();
        for (T refData : this.getReferenceDataset().getData()) {
            dataDistancesMap.put(refData, Math.random()*100);
        }
        dataDistancesMap.put(workingData, -1.); // 600 IQ
        return dataDistancesMap;
    }
}
