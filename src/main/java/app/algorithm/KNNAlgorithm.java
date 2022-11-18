package app.algorithm;

import app.algorithm.geometry.EuclideanGeometry;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class KNNAlgorithm<T extends AbstractData> {
	protected WorkingDataset<T> workingDataset;
	protected KNNCalculator<T> calculator;
	protected KNNStrength<T> strength;
	protected int kNeighbours;

	public KNNAlgorithm(WorkingDataset<T> workingDataset, int k) {
		this.workingDataset = workingDataset;
		this.kNeighbours = k;
		this.strength = new KNNStrength<>(this);
		this.calculator = new KNNCalculator<>(this);
	}

	public KNNCalculator<T> getCalculator() {
		return calculator;
	}

	public WorkingDataset<T> getWorkingDataset() {
		return workingDataset;
	}

	public ReferenceDataset<T> getReferenceDataset() {
		return workingDataset.getReferenceDataset();
	}

	public KNNStrength<T> getStrengthObject() { return this.strength; }

	public double getStrength() { return this.strength.getStrength(); }

	public int getKNeighbours() {
		return kNeighbours;
	}

	/**
	 * Return for each working datas an Entry
	 * The key of this entry is the working data
	 * The value of this entry is a List containing is k nearest reference neighbours.
	 * @return List<Entry<T, List<T>>> knn
	 */
	public List<Entry<T, List<T>>> getDatasKNN() {
		return generateKNNResults(this.calculator.launchCalcul(new EuclideanGeometry<>(calculator)));
	}
	
	private List<Entry<T, List<T>>> generateKNNResults(List<Map<T, Double>> dataWithDistances) {
		List<Entry<T, List<T>>> res = new ArrayList<>();
		List<Entry<T, Double>> sortedDatasEntries;
		for (Map<T, Double> dataDistMap : dataWithDistances) {
			// Transform the map in a list of k+1 entries sorted by ascending value (distance)
			sortedDatasEntries = dataDistMap.entrySet().stream()
					.sorted(Map.Entry.comparingByValue())
					.limit(this.kNeighbours+1L)
					.collect(Collectors.toList());
			
			// Using the sorted list to generate the Entry with as key the working data 
			// and as value the list of knn for this working data.
			List<T> neighbours = new ArrayList<>();
			for (int i = 1; i < sortedDatasEntries.size(); i++)
				neighbours.add(sortedDatasEntries.get(i).getKey());
			res.add(Map.entry(sortedDatasEntries.get(0).getKey(), neighbours));
		}
		return res;
	}

	public String toString() {
		return this.kNeighbours + "NNAlgorithm (" + this.getStrength() + "%)";
	}
}