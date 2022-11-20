package app.graphics.models.datas.data;

import com.opencsv.bean.CsvBindByName;

public class TitanicPassengerData extends AbstractData {
    @CsvBindByName(column = "PassengerId")
    public int id;

    @CsvBindByName(column = "Survived")
    public boolean survived;

    @CsvBindByName(column = "Pclass")
    public double pClass;

    @CsvBindByName(column = "Name")
    public String name;

    @CsvBindByName(column = "Sex")
    public String sex;

    @CsvBindByName(column = "Age", required = false)
    public double age;

    @CsvBindByName(column = "SibSp")
    public double sibSp;

    @CsvBindByName(column = "Parch")
    public double parch;

    @CsvBindByName(column = "Ticket")
    public String ticket;

    @CsvBindByName(column = "Fare")
    public double fare;

    @CsvBindByName(column = "Cabin", required = false)
    public String cabin;

    @CsvBindByName(column = "Embarked")
    public char embarked;

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
