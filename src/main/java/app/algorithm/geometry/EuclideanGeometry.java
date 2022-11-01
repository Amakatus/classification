package app.algorithm.geometry;

import java.util.List;

import app.exceptions.FieldNotDoubleException;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.Data;
import app.utils.ClassUtils;

public class EuclideanGeometry<T extends Data> extends GeometryCalculator<T> {
	public final int POWER = 2;
	
	public EuclideanGeometry(List<String> fieldsNames) {
		super(fieldsNames);
	}
	
	public EuclideanGeometry() {
		super();
	}

	@Override
	public double distance(T workingData, T referenceData) throws FieldToDistanceException {
		double sumPow = 0;
		double distance;
		for (String fieldName : this.fieldsNames) {
			distance = this.findDistanceForField(workingData, referenceData, fieldName);
			if(distance == Double.MIN_VALUE) throw new FieldToDistanceException(fieldName);
			else sumPow += Math.pow(distance, POWER);
		}
		return Math.sqrt(sumPow);
	}
}
