package app.graphics.models.datas;

import java.util.ArrayList;
import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.Data;

public class WorkingDataset<T extends Data> extends Dataset<T> {
	protected String categoryField;
	protected List<String> distanceFields;
	protected List<KNNAlgorithm<T>> algorithms;

	public WorkingDataset(String title, List<T> datas, String categoryField, List<String> distanceFields) {
		super(title, datas);
		this.categoryField = categoryField;
		this.distanceFields = distanceFields;
		this.algorithms = new ArrayList<KNNAlgorithm<T>>();
	}

	public WorkingDataset(String title, List<T> datas, String categoryField) {
		this(title, datas, categoryField, new ArrayList<String>());
	}

	public WorkingDataset(String title, List<T> datas) {
		this(title, datas, null);
	}

	public WorkingDataset(String title) {
		this(title, new ArrayList<T>());
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

	public void addAlgorithm(KNNAlgorithm<T> algorithm) {
		this.algorithms.add(algorithm);
	}

	public void addDistanceFieldString(String distanceFieldName) {
		this.distanceFields.add(distanceFieldName);
	}

	public void removeDistanceFieldString(String distanceFieldName) {
		this.distanceFields.remove(distanceFieldName);
	}

	public void setCategoryField(String categoryFieldName) {
		this.categoryField = categoryFieldName;
	}
}