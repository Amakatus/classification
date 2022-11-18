package app.graphics.components;

import java.util.List;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.models.datas.points.IPoint;

public class MyScatterChart<T extends AbstractData> {
	protected KNNAlgorithm<T> algorithm;
	protected List<IPoint> points;
	protected String yColName;
	protected String xColName;
}
