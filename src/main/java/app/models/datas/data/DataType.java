package app.models.datas.data;

import java.lang.reflect.Field;

public enum DataType {
    IRIS(IrisData.class, "iris"),
    PASSENGER(TitanicPassengerData.class, "titanic");

    Class<? extends AbstractData> typeClass;
    String csvPath;

    DataType(Class<? extends AbstractData> typeClass, String csvPath) {
        this.typeClass = typeClass;
        this.csvPath = csvPath;
    }

    public Class<? extends AbstractData> getTypeClass() {
        return this.typeClass;
    }
    
    public String getCsvName() {
    	return this.csvPath;
    }

    public String getCsvPath() {
        return String.format("/data/%s.csv", this.getCsvName());
    }

    public Field[] getFields() {
        return this.typeClass.getDeclaredFields();
    }
}