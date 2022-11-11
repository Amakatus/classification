package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.exceptions.FieldNotDoubleException;
import app.graphics.models.datas.data.Data;
import app.utils.ClassUtils;

public abstract class GeometryCalculator<T extends Data> implements IGeometryCalculator<T> {
	protected KNNCalculator<T> calculator;

	protected GeometryCalculator(KNNCalculator<T> calculator) {
		this.calculator = calculator;
	}
	
	protected double findDistanceForField(T workingData, T referenceData, String fieldName) {
		try {
			return ClassUtils.getDoubleFromField(workingData,fieldName) - ClassUtils.getDoubleFromField(referenceData, fieldName);
		} catch (FieldNotDoubleException exception) {
			return ClassUtils.getValueFromFieldByMethod(workingData, fieldName, referenceData);
		}
	}
}
