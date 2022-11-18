package app.graphics.models.datas;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.Data;

import java.util.ArrayList;
import java.util.List;

public class WorkingDataset<T extends Data> extends Dataset<T> {
	protected String categoryField;
	protected List<String> distanceFields;
	protected List<KNNAlgorithm<T>> algorithms;
	protected ReferenceDataset<T> referenceDataset;

	public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField,
			List<String> distanceFields) {
		super(title, datas);
		this.categoryField = categoryField;
		this.distanceFields = distanceFields;
		this.algorithms = new ArrayList<>();
		this.referenceDataset = referenceDataset;
	}

	public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField) {
		this(title, datas, referenceDataset, categoryField, new ArrayList<>());
	}

	public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset) {
		this(title, datas, referenceDataset, null);
	}

	public WorkingDataset(String title, ReferenceDataset<T> referenceDataset) {
		this(title, new ArrayList<>(), referenceDataset);
	}

	public List<KNNAlgorithm<T>> getAlgorithms() {
		return this.algorithms;
	}

	public String getCategoryField() {
		return categoryField;
	}

	public List<String> getDistanceFields() {
		return distanceFields;
	}
	
	public void createAlgorithm(int k) {
		AlgorithmFactory.createKNN(this, k);
	}

	public void addAlgorithm(KNNAlgorithm<T> algorithm) {
		this.algorithms.add(algorithm);
	}

	public void addDistanceFieldString(String distanceFieldName) {
		if (!this.distanceFields.contains(distanceFieldName)) {
			this.distanceFields.add(distanceFieldName);
		}

	}

	public void removeDistanceFieldString(String distanceFieldName) {
		this.distanceFields.remove(distanceFieldName);
	}

	public void setCategoryField(String categoryFieldName) {
		this.categoryField = categoryFieldName;
	}

	public ReferenceDataset<T> getReferenceDataset() {
		return this.referenceDataset;
	}
}