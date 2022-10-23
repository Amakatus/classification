package app.datas.points;

import app.datas.data.Data;

public class Point<T extends Data> implements IPoint {
	protected double xPos;
	protected double yPos;
	protected T data;
}
