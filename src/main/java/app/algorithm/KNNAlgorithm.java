package app.algorithm;

import app.algorithm.geometry.IGeometryCalculator;
import app.graphics.components.MyScatterChart;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.data.Data;

public class KNNAlgorithm<T extends Data> {
	protected IGeometryCalculator<T> calculator;
	protected Dataset<T> workingDataset;
	protected Dataset<T> referenceDataset;
	protected double strength;
	protected int kNeighbours;
	
	public KNNAlgorithm(Dataset<T> workingDataset, Dataset<T> referenceDataset, int k) {
		this.workingDataset = workingDataset;
		this.referenceDataset = referenceDataset;
		this.kNeighbours = k;
		this.strength = Math.round(Math.random()*100);
		this.workingDataset.addAlgorithm(this);
	}
	
	public int getK() {
		return this.kNeighbours;
	}
	
	public MyScatterChart<T> generateScatterChart() {
		return null;
	}
	
	public String toString() {
		return this.kNeighbours+"NNAlgorithm ("+this.strength+"%)";
	}
}