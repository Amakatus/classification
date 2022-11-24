package app.models.algorithm.calculators;

import app.models.algorithm.geometry.IGeometry;
import app.models.datas.data.AbstractData;

import java.util.Map;

public interface ICalculator<T extends AbstractData> {
    Map<T, Double> getDistances(T workingData);

    IGeometry<T> getGeometry();
}
