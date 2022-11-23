package app.models.datas.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeTest {
    @Test
    void testDataTypes() {
        assertEquals(IrisData.class, DataType.IRIS.getTypeClass());
        assertEquals(TitanicPassengerData.class, DataType.PASSENGER.getTypeClass());
    }
}