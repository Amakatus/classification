package app.utils;

import app.exceptions.FieldNotNumberException;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClassUtilsTest {
    TestClassUtils testClass;
    TestClassUtilsTwo testClassTwo;

    @BeforeEach
    void init() {
        this.testClass = new TestClassUtils();
        this.testClassTwo = new TestClassUtilsTwo();
    }

    @Test
    void testGetFields() {
        Field[] fieldsByUtils = ClassUtils.getFields(this.testClass);
        Field[] fields = this.testClass.getClass().getDeclaredFields();
        assertEquals(fields.length, fieldsByUtils.length);
        assertArrayEquals(fields, fieldsByUtils);
        assertEquals(0, ClassUtils.getFields(testClassTwo).length);
    }

    @Test
    void testGetNumberFields() {
        ArrayList<Field> fields = (ArrayList<Field>) ClassUtils.getNumberFields(new IrisData());
        assertEquals(4, fields.size());
    }

    @Test
    void testGetMethods() {
        Method[] methodsByUtils = ClassUtils.getMethods(this.testClass);
        Method[] methods = this.testClass.getClass().getDeclaredMethods();
        assertEquals(methods.length, methodsByUtils.length);
        assertArrayEquals(methods, methodsByUtils);
    }

    @Test
    void testFindMethodByName() {
        Method irisGetVariety = ClassUtils.findMethodByName(this.testClass, "getVariety");
        Method irisGetVaryety = ClassUtils.findMethodByName(this.testClass, "getVaryety");
        assertNull(irisGetVaryety);
        assertEquals(String.class, irisGetVariety.getReturnType());
    }

    @Test
    void testHasMethodToDoubleForField() {
        assertTrue(ClassUtils.hasMethodToDoubleForField(this.testClass, "variety"));
        assertFalse(ClassUtils.hasMethodToDoubleForField(this.testClassTwo, "variety"));
    }

    @Test
    void testGetValueFromFieldByMethod() {
        TestClassUtils otherObject = new TestClassUtils();
        otherObject.setVariety("test");
        assertEquals(1, ClassUtils.getValueFromFieldByMethod(testClass, "variety", testClass));
        assertEquals(0, ClassUtils.getValueFromFieldByMethod(testClass, "variety", otherObject));
    }

    @Test
    void testGetDoubleFromFieldObjectField() {
        testGetDoubleFromFieldObjectString();
    }

    @Test
    void testGetDoubleFromFieldObjectString() {
        try {
            assertEquals(0, ClassUtils.getDoubleFromField(testClass, "size"));
            assertEquals(Double.MIN_VALUE, ClassUtils.getDoubleFromField(testClass, "sizee"));
        } catch (FieldNotNumberException e) {
            fail();
        }
    }

    @Test
    void test_field_can_be_used_as_category_field() {
        IrisData irisData = new IrisData();
        TitanicPassengerData passengerData = new TitanicPassengerData();
        assertTrue(ClassUtils.canBeCategoryField(ClassUtils.getFieldByName(irisData, "variety")));
        assertFalse(ClassUtils.canBeCategoryField(ClassUtils.getFieldByName(irisData, "petalLength")));
        assertTrue(ClassUtils.canBeCategoryField(ClassUtils.getFieldByName(passengerData, "survived")));
        assertTrue(ClassUtils.canBeCategoryField(ClassUtils.getFieldByName(passengerData, "sex")));
        assertFalse(ClassUtils.canBeCategoryField(ClassUtils.getFieldByName(passengerData, "age")));
    }

    @Test
    void testGetObjectFromField() {
        TitanicPassengerData passenger = new TitanicPassengerData();
        passenger.setSurvived(true);
        passenger.setEmbarked('a');
        passenger.setTicket("Ticket");
        assertEquals(true, ClassUtils.getValueObjectFromField(passenger, "survived"));
        assertEquals('a', ClassUtils.getValueObjectFromField(passenger, "embarked"));
        assertEquals("Ticket", ClassUtils.getValueObjectFromField(passenger, "ticket"));
        assertNull(ClassUtils.getValueObjectFromField(passenger, "name"));
    }
}
