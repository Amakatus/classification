package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DataTypesTest {

	@Test
	void testDataTypes() {
		assertEquals(IrisData.class, DataType.IRIS.getTypeClass());
		assertEquals(TitanicPassengerData.class, DataType.PASSENGER.getTypeClass());
	}

}
