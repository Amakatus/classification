package app.algorithm;

import app.algorithm.geometry.IGeometryCalculator;
import app.graphics.components.MyScatterChart;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.data.Data;

public class KNNAlgorithm<T extends Data> {
	protected IGeometryCalculator<T> calculator;
	protected Dataset<T> dataSet;
	protected double strength;
	protected int kNeighbours;
	
	public KNNAlgorithm(Dataset<T> dataSet, int k) {
		this.dataSet = dataSet;
		this.kNeighbours = k;
		this.strength = Math.round(Math.random()*100);
		this.dataSet.addAlgorithm(this);
	}
	
	public MyScatterChart<T> generateScatterChart() {
		return null;
	}
	
	public String toString() {
		return this.kNeighbours+"NNAlgorithm ("+this.strength+"%)";
	}
}