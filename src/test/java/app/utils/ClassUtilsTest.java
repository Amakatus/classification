package app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.exceptions.FieldNotDoubleException;

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
		assertTrue(Arrays.equals(fields, fieldsByUtils));
		assertEquals(0, ClassUtils.getFields(testClassTwo).length);
	}

	@Test
	void testGetMethods() {
		Method[] methodsByUtils = ClassUtils.getMethods(this.testClass);
		Method[] methods = this.testClass.getClass().getDeclaredMethods();
		assertEquals(methods.length, methodsByUtils.length);
		assertTrue(Arrays.equals(methods, methodsByUtils));
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
	void testGetCalculableFields() {
		assertEquals(2, ClassUtils.getCalculableFields(this.testClass).size());
		assertEquals(0, ClassUtils.getCalculableFields(this.testClassTwo).size());
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
			assertEquals(-1, ClassUtils.getDoubleFromField(testClass, "sizee"));
		} catch (FieldNotDoubleException e) {
			assertTrue(false);
		}
	}

}
