package app.graphics.models;

import java.util.ArrayList;
import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.datas.columns.Column;
import app.datas.data.Data;

public class Dataset<T extends Data> implements Model {
	protected List<Column<T>> columns;
	protected List<T> datas;
	protected List<KNNAlgorithm<T>> algorithms;
	protected String title;
	
	public Dataset(String title) {
		this.title = title;
		this.columns = new ArrayList<Column<T>>();
		this.algorithms = new ArrayList<KNNAlgorithm<T>>();
		this.datas = new ArrayList<>();
	}

	public String getTitle() { return this.title; }
	public List<KNNAlgorithm<T>> getAlgorithms() {
		return this.algorithms;
	}
	
	public void addKNNAlgorithm(KNNAlgorithm<T> algorithm) {
		this.algorithms.add(algorithm);
	}
	
	public void addData(T data) {
		this.datas.add(data);
	}
	
	public String toString() {
		return String.format("%s", this.title);
	}
}