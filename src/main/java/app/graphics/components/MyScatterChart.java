package app.graphics.components;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.models.datas.points.IPoint;

import java.util.List;

public class MyScatterChart<T extends AbstractData> {
    protected KNNAlgorithm<T> algorithm;
    protected List<IPoint> points;
    protected String yColName;
    protected String xColName;
}
