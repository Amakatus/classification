package app.algorithm.geometry;

import java.util.List;

import app.graphics.models.datas.data.Data;

public abstract class GeometryCalculator<T extends Data> implements IGeometryCalculator<T> {
	protected List<String> fieldsNames;

	public GeometryCalculator(List<String> fieldsNames) {
		this.fieldsNames = fieldsNames;
	}

	public abstract double distance(T workingData, T referenceData);
	
	public void addField(String fieldName) {
		this.fieldsNames.add(fieldName);
	}
	
	public void removeFieldName(String fieldName) {
		this.fieldsNames.remove(fieldName);
	}
	
	public List<String> getFieldsNames(){
		return this.fieldsNames;
	}
}
