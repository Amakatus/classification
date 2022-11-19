package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.graphics.models.datas.data.AbstractData;

public interface GeometryFactory{
	public static <T extends AbstractData> ManhattanGeometry<T> createManhattanGeometry(KNNCalculator<T> calculator){
		return new ManhattanGeometry<>(calculator);
	}
	public static <T extends AbstractData> EuclideanGeometry<T> createEuclideanGeometry(KNNCalculator<T> calculator){
		return new EuclideanGeometry<>(calculator);
	}
}