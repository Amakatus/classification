package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.exceptions.FieldNotNumberException;
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
		} catch (FieldNotNumberException exception) {
			double fromMethod = ClassUtils.getValueFromFieldByMethod(workingData, fieldName, referenceData);
			if(fromMethod != Double.MAX_VALUE){
				return fromMethod;
			} else {
				Object wValue = ClassUtils.getValueObjectFromField(workingData, fieldName);
				Object rValue = ClassUtils.getValueObjectFromField(referenceData, fieldName);
				if(wValue == null || rValue == null) return Double.MAX_VALUE;
				return GeneralToDouble.toDouble(wValue, rValue);
			}
		}
	}
}
