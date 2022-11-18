package app.graphics.models.datas.data;

public enum DataType {
	IRIS(IrisData.class, "iris"),
	PASSENGER(TitanicPassengerData.class, "titanic");

	Class<? extends Data> typeClass;
	String csvPath;
	
	DataType(Class<? extends Data> typeClass, String csvPath) {
		this.typeClass = typeClass;
		this.csvPath = csvPath;
	}
	
	public Class<? extends Data> getTypeClass() {
		return this.typeClass;
	}
	public String getCsvPath() {
		return String.format("/data/%s.csv", this.csvPath);
	}
}