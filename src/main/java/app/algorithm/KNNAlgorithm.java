package app.algorithm;

import java.util.List;
import java.util.Map;

import app.algorithm.geometry.IGeometryCalculator;
import app.graphics.components.MyScatterChart;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;

public class KNNAlgorithm<T extends Data> {
	protected IGeometryCalculator<T> calculator;
	protected WorkingDataset<T> workingDataset;
	protected ReferenceDataset<T> referenceDataset;
	protected List<Map<T, Double>> dataWithDistances;
	protected double strength;
	protected int kNeighbours;
	
	public KNNAlgorithm(WorkingDataset<T> workingDataset, ReferenceDataset<T> referenceDataset, int k) {
		this.workingDataset = workingDataset;
		this.referenceDataset = referenceDataset;
		this.kNeighbours = k;
		this.strength = Math.round(Math.random()*100);
	}
	
	public int getK() {
		return this.kNeighbours;
	}
	
	public MyScatterChart<T> generateScatterChart() {
		return null;
	}
	
	public void createCalculator() {
		
	}
	
	public void launchDistancesCalcul() {
		
	}
	
	public String toString() {
		return this.kNeighbours+"NNAlgorithm ("+this.strength+"%)";
	}
}