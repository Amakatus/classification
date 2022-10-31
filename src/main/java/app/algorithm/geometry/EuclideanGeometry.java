package app.algorithm.geometry;

import java.util.List;

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
		double workingFieldValue;
		double referenceFieldValue;
		for (String fieldName : this.fieldsNames) {
			workingFieldValue = workingData.getValueFromFieldName(fieldName);
			referenceFieldValue = referenceData.getValueFromFieldName(fieldName);
			if(workingFieldValue < 0 || referenceFieldValue < 0) {
				System.err.println(String.format("Couldn't transform field '%s' to double, ignoring.", fieldName));
				continue;
			}
			sumToPow += Math.pow(
					workingData.getValueFromFieldName(fieldName) - referenceData.getValueFromFieldName(fieldName),
					POWER);
		}
		return Math.sqrt(sumToPow);
	}
}
