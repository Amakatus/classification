package app.models.datas.data;

import com.opencsv.bean.CsvBindByName;

public class TitanicPassengerData extends AbstractData {
    @CsvBindByName(column = "PassengerId")
    protected int id;

    @CsvBindByName(column = "Survived")
    protected boolean survived;

    @CsvBindByName(column = "Pclass")
    protected double pClass;

    @CsvBindByName(column = "Name")
    protected String name;

    @CsvBindByName(column = "Sex")
    protected String sex;

    @CsvBindByName(column = "Age", required = false)
    protected double age;

    @CsvBindByName(column = "SibSp")
    protected double sibSp;

    @CsvBindByName(column = "Parch")
    protected double parch;

    @CsvBindByName(column = "Ticket")
    protected String ticket;

    @CsvBindByName(column = "Fare")
    protected double fare;

    @CsvBindByName(column = "Cabin", required = false)
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

    public double getpClass() {
        return pClass;
    }

    public void setpClass(double pClass) {
        this.pClass = pClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getSibSp() {
        return sibSp;
    }

    public void setSibSp(double sibSp) {
        this.sibSp = sibSp;
    }

    public double getParch() {
        return parch;
    }

    public void setParch(double parch) {
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
