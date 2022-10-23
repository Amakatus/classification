package app.graphics.models;

import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.datas.columns.Column;
import app.datas.data.Data;

public class DataSet<T extends Data> implements Model {
	protected List<Column<T>> columns;
	protected List<T> lines;
	protected List<KNNAlgorithm<T>> algorithms;
	protected String title;
}