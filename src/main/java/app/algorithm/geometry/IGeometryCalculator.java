package app.algorithm.geometry;

import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.AbstractData;

public interface IGeometryCalculator<T extends AbstractData> {
    double distance(T workingData, T referenceData) throws FieldToDistanceException;
}
