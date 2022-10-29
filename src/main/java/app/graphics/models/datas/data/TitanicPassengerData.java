package app.graphics.models.datas.data;

import com.opencsv.bean.CsvBindByName;

public class TitanicPassengerData extends Data {
	@CsvBindByName(column = "PassengerId")
	protected int id;
	
	@CsvBindByName(column = "Survived")
	protected boolean survived;
	
	@CsvBindByName(column = "Pclass")
	protected int pClass;
	
	@CsvBindByName(column = "Name")
	protected String name;
	
	@CsvBindByName(column = "Sex")
	protected boolean male;
	
	@CsvBindByName(column = "Age", required=false)
	protected int age;
	
	@CsvBindByName(column = "SibSp")
	protected int sibSp;
	
	@CsvBindByName(column = "Parch")
	protected int parch;
	
	@CsvBindByName(column = "Ticket")
	protected String ticket;
	
	@CsvBindByName(column = "Fare")
	protected double fare;
	
	@CsvBindByName(column = "Cabin", required=false)
	protected String cabin;
	
	@CsvBindByName(column = "Embarked")
	protected char embarked;
}
