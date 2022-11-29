package app.models.datas.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import app.utils.ClassUtils;

class DataTypeTest {
    @Test
    void testDataTypes() {
        assertEquals(IrisData.class, DataType.IRIS.getTypeClass());
        assertEquals(TitanicPassengerData.class, DataType.PASSENGER.getTypeClass());
    }
    
    @Test
    void test_get_fields_from_enum() {
    	Field[] enumFields = DataType.IRIS.getFields();
    	Field[] objectFields = ClassUtils.getFields(new IrisData());
    	assertEquals(objectFields.length, enumFields.length);
    	for(int i = 0; i < enumFields.length; i++) {
    		assertEquals(objectFields[i],enumFields[i]);
    	}
    }
}