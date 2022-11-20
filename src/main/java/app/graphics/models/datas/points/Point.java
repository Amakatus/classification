package app.graphics.models.datas.points;

import app.graphics.models.datas.data.AbstractData;

public class Point<T extends AbstractData> implements IPoint {
    protected double xPos;
    protected double yPos;
    protected T data;

    /**
     * @return the xPos
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
