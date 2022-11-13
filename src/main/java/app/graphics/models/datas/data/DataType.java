package app.graphics.models.datas.data;

public enum DataType {
	IRIS(IrisData.class),
	PASSENGER(TitanicPassengerData.class);

	Class<? extends Data> typeClass;
	
	DataType(Class<? extends Data> typeClass) {
		this.typeClass = typeClass;
	}
	
	public Class<? extends Data> getTypeClass() {
		return this.typeClass;
	}
}