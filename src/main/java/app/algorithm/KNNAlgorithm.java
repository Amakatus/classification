package app.algorithm;

import app.algorithm.geometry.IGeometryCalculator;
import app.datas.data.Data;
import app.graphics.components.MyScatterChart;
import app.graphics.models.DataSet;

public class KNNAlgorithm<T extends Data> {
	protected IGeometryCalculator<T> calculator;
	protected DataSet<T> dataSet;
	protected double strength;
	protected int kNeighbours;
	
	public MyScatterChart<T> generateScatterChart() {
		return null;
	}
}