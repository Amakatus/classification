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
}