package app.algorithm;

import app.algorithm.geometry.IGeometryCalculator;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.Data;
import app.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNNCalculator<T extends Data> {
	protected IGeometryCalculator<T> geometry;
	protected KNNAlgorithm<T> algorithm;
	protected List<Map<T, Double>> datasWithDistances;
	
	public KNNCalculator(KNNAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
		this.datasWithDistances = new ArrayList<>();
	}

	public KNNAlgorithm<T> getAlgorithm() {
		return this.algorithm;
	}
	
	public IGeometryCalculator<T> getGeometry() {
		return this.geometry;
	}

	public List<Map<T, Double>> getDatasWithDistances() {
		return this.datasWithDistances;
	}
	
	public List<String> getFieldsNames() {
		return this.getWorkingDataset().getDistanceFields();
	}
	
	public WorkingDataset<T> getWorkingDataset() {
		return this.algorithm.getWorkingDataset();
	}
	
	public ReferenceDataset<T> getReferenceDataset() {
		return this.algorithm.getReferenceDataset();
	}
	
	public List<Map<T, Double>> launchCalcul(IGeometryCalculator<T> geometry) {
		this.datasWithDistances.clear();
		this.geometry = geometry;
		this.setupDistances();
		return this.datasWithDistances;
	}
	
	/**
	 * Construct one map per working data
	 * This map contains all the reference values and the working data for which
	 * we are currently calculating the distances.
	 */
	private void setupDistances() {
		for (T workingData : this.getWorkingDataset().getDatas()) {
			this.datasWithDistances.add(this.getDistancesForData(workingData));
		}
	}

	private Map<T, Double> getDistancesForData(T workingData) {
		Map<T, Double> dataDistancesMap = new HashMap<>();
		dataDistancesMap.put(workingData, -1.); // 600 IQ
		for (T refData : this.getReferenceDataset().getDatas()) {
			getDistanceBetweenDatas(workingData, dataDistancesMap, refData);
		}
		return dataDistancesMap;
	}

	private void getDistanceBetweenDatas(T workingData, Map<T, Double> dataDistancesMap, T refData) {
		try {
			dataDistancesMap.put(refData, this.geometry.distance(workingData, refData));
		} catch (FieldToDistanceException e) {
			dataDistancesMap.put(refData, Double.MAX_VALUE);
			Logger.exception(e);
		}
	}
}