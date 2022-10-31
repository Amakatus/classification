package app.algorithm.geometry;

import java.util.List;

import app.exceptions.FieldToDistanceException;
import app.exceptions.FieldNotDoubleException;
import app.graphics.models.datas.data.Data;

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
		double sumToPow = 0;
		double distance;
		for (String fieldName : this.fieldsNames) {
			distance = findDistanceForField(workingData, referenceData, fieldName);
			if(distance >= 0)
				sumToPow += distance;
			else
				throw new FieldToDistanceException("Ignoring " + fieldName + " because cant find "+fieldName+"ToDistance method that return a valid distance.");
		}
		return Math.sqrt(sumToPow);
	}

	private double findDistanceForField(T workingData, T referenceData, String fieldName) {
		try {
			return Math.pow(
					workingData.getValueFromFieldName(fieldName) - referenceData.getValueFromFieldName(fieldName),
					POWER);
		} catch (FieldNotDoubleException e1) {
			return workingData.getValueFromFieldByMethod(fieldName, referenceData);
		}
	}
}
