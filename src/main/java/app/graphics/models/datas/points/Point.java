package app.graphics.models.datas.points;

import app.graphics.models.datas.data.Data;

public class Point<T extends Data> implements IPoint {
	protected double xPos;
	protected double yPos;
	protected T data;
}
