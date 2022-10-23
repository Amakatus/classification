package app.graphics.components;

import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.datas.data.Data;
import app.datas.points.IPoint;

public class MyScatterChart<T extends Data> {
	protected KNNAlgorithm<T> algorithm;
	protected List<IPoint> points;
	protected String yColName;
	protected String xColName;
}
