package app.algorithm.geometry;

import java.util.List;

import app.exceptions.RequestedFieldNotDouble;
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
	public double distance(T workingData, T referenceData) {
		double sumToPow = 0;
		double distance;
		for (String fieldName : this.fieldsNames) {
			distance = findDistanceForField(workingData, referenceData, fieldName);
			if(distance >= 0)
				sumToPow += distance;
			else
				System.err.println("Ignoring " + fieldName + " because cant find "+fieldName+"ToDistance method that return a valid distance.");
		}
		return Math.sqrt(sumToPow);
	}

	private double findDistanceForField(T workingData, T referenceData, String fieldName) {
		double distance;
		try {
			distance = Math.pow(
					workingData.getValueFromFieldName(fieldName) - referenceData.getValueFromFieldName(fieldName),
					POWER);
		} catch (RequestedFieldNotDouble e1) {
			distance = workingData.getValueFromFieldByMethod(fieldName, referenceData);
		}
		return distance;
	}
}
