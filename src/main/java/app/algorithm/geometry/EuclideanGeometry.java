package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.AbstractData;

public class EuclideanGeometry<T extends AbstractData> extends AbstractGeometryCalculator<T> {
	public static final int EUCLIDEAN_POWER = 2;
	
	public EuclideanGeometry(KNNCalculator<T> calculator) {
		super(calculator);
	}

	@Override
	public double distance(T workingData, T referenceData) throws FieldToDistanceException {
		double sumPow = 0;
		double distance;
		for (String fieldName : this.calculator.getFieldsNames()) {
			distance = this.findDistanceForField(workingData, referenceData, fieldName);
			if(distance == Double.MAX_VALUE) throw new FieldToDistanceException(fieldName);
			else sumPow += Math.pow(distance, EUCLIDEAN_POWER);
		}
		return Math.sqrt(sumPow);
	}
}
