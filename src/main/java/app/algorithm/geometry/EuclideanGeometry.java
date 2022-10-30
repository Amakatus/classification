package app.algorithm.geometry;

import java.util.List;

import app.graphics.models.datas.data.Data;

public class EuclideanGeometry<T extends Data> extends GeometryCalculator<T> {
	
	public EuclideanGeometry(List<String> fieldsNames) {
		super(fieldsNames);
	}

	@Override
	public double distance(T data1, T data2) {
		return 0;
	}

}
