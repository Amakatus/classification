package app.graphics.models.datas.data;

public enum DataTypes {
	IRIS(IrisData.class),
	PASSENGER(TitanicPassengerData.class);

	Class<? extends Data> typeClass;
	
	DataTypes(Class<? extends Data> typeClass) {
		this.typeClass = typeClass;
	}
	
	public Class<? extends Data> getTypeClass() {
		return this.typeClass;
	}
}
