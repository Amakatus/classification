package app.graphics.models.datas.data;

import com.opencsv.bean.CsvBindByName;

public class IrisData extends AbstractData {
    @CsvBindByName(column = "sepal.length")
    public double sepalLength;

    @CsvBindByName(column = "sepal.width")
    public double sepalWidth;

    @CsvBindByName(column = "petal.length")
    public double petalLength;

    @CsvBindByName(column = "petal.width")
    public double petalWidth;

    @CsvBindByName(column = "variety")
    public IrisVariety variety;

    public double getSepalLength() {
        return sepalLength;
    }

    public void setSepalLength(double sepalLength) {
        this.sepalLength = sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public void setSepalWidth(double sepalWidth) {
        this.sepalWidth = sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public void setPetalLength(double petalLength) {
        this.petalLength = petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    public void setPetalWidth(double petalWidth) {
        this.petalWidth = petalWidth;
    }

    public void setVariety(IrisVariety variety) {
        this.variety = variety;
    }

    public IrisVariety getVariety() {
        return this.variety;
    }

    public double varietyToDouble(AbstractData other) {
        IrisData otherIris = (IrisData) other;
        return (double) this.variety.toString().length() - otherIris.getVariety().toString().length();
    }
}