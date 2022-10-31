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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSurvived() {
		return survived;
	}

	public void setSurvived(boolean survived) {
		this.survived = survived;
	}

	public int getpClass() {
		return pClass;
	}

	public void setpClass(int pClass) {
		this.pClass = pClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSibSp() {
		return sibSp;
	}

	public void setSibSp(int sibSp) {
		this.sibSp = sibSp;
	}

	public int getParch() {
		return parch;
	}

	public void setParch(int parch) {
		this.parch = parch;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public char getEmbarked() {
		return embarked;
	}

	public void setEmbarked(char embarked) {
		this.embarked = embarked;
	}
	
}
