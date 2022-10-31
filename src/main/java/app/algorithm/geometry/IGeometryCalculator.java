package app.algorithm.geometry;

import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.Data;

public interface IGeometryCalculator<T extends Data> {
	double distance(T workingData, T referenceData) throws FieldToDistanceException;
}
