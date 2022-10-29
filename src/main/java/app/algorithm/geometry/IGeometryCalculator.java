package app.algorithm.geometry;

import app.graphics.models.datas.data.Data;

public interface IGeometryCalculator<T extends Data> {
	double distance(T data1, T data2);
}
