package app.graphics.models.datas.data;

import com.opencsv.bean.CsvBindByName;

public class IrisData extends Data {
	@CsvBindByName(column = "sepal.length")
	protected double sepalLength;
	
	@CsvBindByName(column = "sepal.width")
	protected double sepalWidth;
	
	@CsvBindByName(column = "petal.length")
	protected double petalLength;
	
	@CsvBindByName(column = "petal.width")
	protected double petalWidth;
	
	@CsvBindByName(column = "variety")
	protected IrisVariety variety;
	
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
}