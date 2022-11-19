package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.AbstractData;

public class ManhattanGeometry<T extends AbstractData> extends AbstractGeometryCalculator<T> {
	
	public ManhattanGeometry(KNNCalculator<T> calculator) {
		super(calculator);
	}

	@Override
	public double distance(T workingData, T referenceData) throws FieldToDistanceException {
		double sum = 0;
		double distance;
		for (String fieldName : this.calculator.getFieldsNames()) {
			distance = this.findDistanceForField(workingData, referenceData, fieldName);
			if(distance == Double.MAX_VALUE) throw new FieldToDistanceException(fieldName);
			else sum += Math.abs(distance);
		}
		return sum;
	}
}
