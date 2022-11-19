package app.algorithm.geometry;

import app.algorithm.KNNCalculator;
import app.graphics.models.datas.data.AbstractData;

public interface GeometryFactory{
	public static <T extends AbstractData> ManhatthanGeometry<T> createManhattanGeometry(KNNCalculator<T> calculator){
		return new ManhatthanGeometry<>(calculator);
	}
	public static <T extends AbstractData> EuclideanGeometry<T> createEuclideanGeometry(KNNCalculator<T> calculator){
		return new EuclideanGeometry<>(calculator);
	}
}