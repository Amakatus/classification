package app.components;

import app.models.algorithm.KNNAlgorithm;
import app.models.datas.data.AbstractData;
import app.models.datas.points.IPoint;

import java.util.List;

public class MyScatterChart<T extends AbstractData> {
    protected KNNAlgorithm<T> algorithm;
    protected List<IPoint> points;
    protected String yColName;
    protected String xColName;
}
