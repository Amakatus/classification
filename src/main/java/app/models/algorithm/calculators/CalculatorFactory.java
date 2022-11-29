package app.models.algorithm.calculators;

import app.models.algorithm.geometry.IGeometry;
import app.models.datas.ReferenceDataset;
import app.models.datas.data.AbstractData;

public interface CalculatorFactory {
    static <T extends AbstractData> ICalculator<T> createCalculator(String name, ReferenceDataset<T> referenceDataset, IGeometry<T> geometry) {
        if (name.equalsIgnoreCase("Random")) {
            return new RandomDistanceCalculator<>(referenceDataset, geometry);
        } else {
            return new DistanceCalculator<>(referenceDataset, geometry);
        }
    }
}
