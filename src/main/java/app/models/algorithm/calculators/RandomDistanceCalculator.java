package app.models.algorithm.calculators;

import app.models.algorithm.geometry.IGeometry;
import app.models.datas.ReferenceDataset;
import app.models.datas.data.AbstractData;

import java.util.HashMap;
import java.util.Map;

public class RandomDistanceCalculator<T extends AbstractData> extends AbstractCalculator<T> {
    public RandomDistanceCalculator(ReferenceDataset<T> referenceDataset, IGeometry<T> geometry) {
        super(referenceDataset, geometry);
    }

    @Override
    public Map<T, Double> getDistances(T workingData) {
        Map<T, Double> dataDistancesMap = new HashMap<>();
        for (T refData : this.getReferenceDataset().getData()) {
            dataDistancesMap.put(refData, Math.random() * 100);
        }
        dataDistancesMap.put(workingData, -1.); // 600 IQ
        return dataDistancesMap;
    }
    
    public String simpleName() {
    	return "Random";
    }
}
