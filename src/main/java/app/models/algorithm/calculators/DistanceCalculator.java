package app.models.algorithm.calculators;

import app.exceptions.FieldToDistanceException;
import app.models.algorithm.Algorithm;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.data.AbstractData;
import app.utils.LoggerUtils;

import java.util.HashMap;
import java.util.Map;

public class DistanceCalculator<T extends AbstractData> extends AbstractCalculator<T> {
    public DistanceCalculator(Algorithm<T> algorithm, IGeometryCalculator<T> geometry) {
        super(algorithm, geometry);
    }

    public Map<T, Double> getDistances(T workingData) {
        Map<T, Double> dataDistancesMap = new HashMap<>();
        dataDistancesMap.put(workingData, -1.); // 600 IQ
        for (T refData : this.getReferenceDataset().getData()) {
            setDistanceBetweenDatas(workingData, refData, dataDistancesMap);
        }
        return dataDistancesMap;
    }

    private void setDistanceBetweenDatas(T workingData, T refData, Map<T, Double> dataDistancesMap) {
        try {
            dataDistancesMap.put(refData, this.geometry.distance(workingData, refData));
        } catch (FieldToDistanceException e) {
            dataDistancesMap.put(refData, Double.MAX_VALUE);
            LoggerUtils.exception(e);
        }
    }
}