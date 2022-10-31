package app.algorithm.geometry;

import java.util.List;

import app.graphics.models.datas.data.Data;

public class ManhattanGeometry<T extends Data> extends GeometryCalculator<T> {
	public final int POWER = 2;

	public ManhattanGeometry(List<String> fieldsNames) {
		super(fieldsNames);
	}
	
	public ManhattanGeometry() {
		super();
	}

	@Override
	public double distance(T workingData, T referenceData) {
		double sumToPow = 0;
		for (String fieldName : this.fieldsNames) {
			sumToPow += Math.pow(
					workingData.getValueFromFieldName(fieldName) - referenceData.getValueFromFieldName(fieldName),
					POWER);
		}
		return Math.sqrt(sumToPow);
	}
}
