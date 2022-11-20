package app.graphics.models.datas.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataTypesTest {

    @Test
    void testDataTypes() {
        assertEquals(IrisData.class, DataType.IRIS.getTypeClass());
        assertEquals(TitanicPassengerData.class, DataType.PASSENGER.getTypeClass());
    }

}
