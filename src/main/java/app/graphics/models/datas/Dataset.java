package app.graphics.models.datas;

import java.util.ArrayList;
import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.DatasetType;
import app.graphics.models.Model;
import app.graphics.models.datas.columns.Column;
import app.graphics.models.datas.data.Data;

public class Dataset<T extends Data> implements Model {
	protected List<Column<T>> columns;
	protected List<T> datas;
	protected List<KNNAlgorithm<T>> algorithms;
	protected String title;
	protected String categoryField;
	protected List<String> distanceFields;
	protected DatasetType type;

	public Dataset(String title, List<T> datas, List<String> distanceFields, String categoryField, DatasetType type) {
		this.title = title;
		this.columns = new ArrayList<Column<T>>();
		this.algorithms = new ArrayList<KNNAlgorithm<T>>();
		this.datas = datas;
		this.distanceFields = distanceFields;
		this.categoryField = categoryField;
		this.type = type;
	}
	
	public Dataset(String title, List<T> datas, DatasetType type) {
		this(title, datas, new ArrayList<String>(), null,type);
	}
	
	public Dataset(String title, List<T> datas) {
		this(title, datas, DatasetType.REFERENCE);
	}
	
	public Dataset(String title) {
		this(title, new ArrayList<T>());
	}

	public String getTitle() { return this.title; }
	
	public List<KNNAlgorithm<T>> getAlgorithms() {
		return this.algorithms;
	}
	
	public void addAlgorithm(KNNAlgorithm<T> algorithm) {
		this.algorithms.add(algorithm);
	}
	
	public List<T> getDatas() {
		return this.datas;
	}
	
	public void addData(T data) {
		this.datas.add(data);
	}
	
	public String toString() {
		return String.format("%s", this.title);
	}
	
	public DatasetType getType() {
		return type;
	}
}