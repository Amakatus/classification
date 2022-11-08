package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DataTypesTest {

	@Test
	void testDataTypes() {
		assertEquals(IrisData.class, DataTypes.IRIS.getTypeClass());
		assertEquals(TitanicPassengerData.class, DataTypes.PASSENGER.getTypeClass());
	}

}
