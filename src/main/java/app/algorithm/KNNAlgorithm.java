package app.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import app.algorithm.geometry.EuclideanGeometry;
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
		this.strength = Math.round(Math.random() * 100);
		this.dataWithDistances = new ArrayList<>();
	}

	public int getK() {
		return this.kNeighbours;
	}

	public MyScatterChart<T> generateScatterChart() {
		return null;
	}

	public IGeometryCalculator<T> getCalculator() {
		return calculator;
	}

	public WorkingDataset<T> getWorkingDataset() {
		return workingDataset;
	}

	public ReferenceDataset<T> getReferenceDataset() {
		return referenceDataset;
	}

	public List<Map<T, Double>> getDataWithDistances() {
		return dataWithDistances;
	}

	public double getStrength() {
		return strength;
	}

	public int getkNeighbours() {
		return kNeighbours;
	}

	private void createCalculator() {
		this.calculator = new EuclideanGeometry<T>(this.workingDataset.getDistanceFields());
	}

	private void launchDistancesCalcul() {
		this.createCalculator();
		this.getDistancesFromCalculator();
	}

	// Peut être déplacer tout ça dans un objet Calculateur qui possède un Geometry et retourne ça
	private void getDistancesFromCalculator() {
		Map<T, Double> dataDistancesMap;
		for (T workingData : this.workingDataset.getDatas()) {
			dataDistancesMap = new HashMap<T, Double>();
			dataDistancesMap.put(workingData, -1.); // 600 IQ
			for (T refData : this.referenceDataset.getDatas()) {
				dataDistancesMap.put(refData, this.calculator.distance(workingData, refData));
			}
			this.getDataWithDistances().add(dataDistancesMap);
		}
	}

	// Peut être déplacer tout ça dans un objet Calculateur qui possède un Geometry et retourne ça
	public List<Entry<T, List<T>>> getDatasKNN() {
		this.launchDistancesCalcul();
		List<Entry<T, List<T>>> res = new ArrayList<>();
		for (Map<T, Double> dataDistMap : this.dataWithDistances) {
			List<Entry<T, Double>> sortedDatasEntries = dataDistMap.entrySet().stream()
					.sorted(Map.Entry.comparingByValue())
					.limit(kNeighbours+1)
					.collect(Collectors.toList());
			List<T> neighbours = new ArrayList<>();
			for (int i = 1; i < sortedDatasEntries.size(); i++) {
				neighbours.add(sortedDatasEntries.get(i).getKey());
			}
			Entry<T, List<T>> oneDataMap = Map.entry(sortedDatasEntries.get(0).getKey(), neighbours);
			res.add(oneDataMap);
		}
		return res;
	}

	public String toString() {
		return this.kNeighbours + "NNAlgorithm (" + this.strength + "%)";
	}
}