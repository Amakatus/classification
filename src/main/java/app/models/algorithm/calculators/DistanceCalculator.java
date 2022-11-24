package app.models.algorithm.calculators;

import app.exceptions.FieldToDistanceException;
import app.models.algorithm.geometry.IGeometry;
import app.models.datas.ReferenceDataset;
import app.models.datas.data.AbstractData;
import app.utils.LoggerUtils;

import java.util.HashMap;
import java.util.Map;

public class DistanceCalculator<T extends AbstractData> extends AbstractCalculator<T> {
    public DistanceCalculator(ReferenceDataset<T> referenceDataset, IGeometry<T> geometry) {
        super(referenceDataset, geometry);
    }

    // Construct a map, with as key the reference data and as value its distance with the given working data.
    public Map<T, Double> getDistances(T workingData) {
        Map<T, Double> dataDistancesMap = new HashMap<>();
        dataDistancesMap.put(workingData, -1.); // 600 IQ
        for (T refData : this.getReferenceDataset().getData()) {
            setDistanceBetweenDatas(workingData, refData, dataDistancesMap);
        }
        return dataDistancesMap;
    }

    // Add an entry in the map with as key the refData and as value the distance between 
    // the ref data and the working data.
    private void setDistanceBetweenDatas(T workingData, T refData, Map<T, Double> dataDistancesMap) {
        try {
            dataDistancesMap.put(refData, this.geometry.distance(workingData, refData));
        } catch (FieldToDistanceException e) {
            dataDistancesMap.put(refData, Double.MAX_VALUE);
            LoggerUtils.exception(e);
        }
    }
}