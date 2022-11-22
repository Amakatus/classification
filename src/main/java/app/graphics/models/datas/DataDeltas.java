package app.graphics.models.datas;

public class DataDeltas {
    protected double min;
    protected double max;

    public DataDeltas(double min, double max) {
        this.max = max;
        this.min = min;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getDelta() {
        return this.max - this.min;
    }

    public double getNormalizedValue(double initialValue) {
        double newValue = (initialValue - this.min) / this.getDelta();
        if (newValue > 1) newValue = 1;
        else if (newValue < 0) newValue = 0;
        return newValue;
    }

    public double getUnormalizedValue(double initalValue) {
        return initalValue * this.getDelta() + this.min;
    }
}
