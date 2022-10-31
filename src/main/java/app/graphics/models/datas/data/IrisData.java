package app.graphics.models.datas.data;

import com.opencsv.bean.CsvBindByName;

import app.annotations.CalculableField;

public class IrisData extends Data {
	@CsvBindByName(column = "sepal.length")
	@CalculableField
	protected double sepalLength;
	
	@CsvBindByName(column = "sepal.width")
	@CalculableField
	protected double sepalWidth;
	
	@CsvBindByName(column = "petal.length")
	@CalculableField
	protected double petalLength;
	
	@CsvBindByName(column = "petal.width")
	@CalculableField
	protected double petalWidth;
	
	@CsvBindByName(column = "variety")
	@CalculableField
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
	
	public double varietyToDouble() {
		return this.variety.toString().length();
	}
}