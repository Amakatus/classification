package app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.exceptions.FieldNotDoubleException;
import app.graphics.models.datas.data.Data;

public abstract class ClassUtils {
	public static Field[] getFields(Object object) {
		return object.getClass().getDeclaredFields();
	}
	
	public static Method[] getMethods(Object object) {
		return object.getClass().getDeclaredMethods();
	}

	public static Method findMethodByName(Object object, String methodName) {
		Method res = null;
		Method[] methods = getMethods(object);
		boolean found = false;
		int i = 0;
		while (!found && i < methods.length) {
			if (methods[i].getName().equalsIgnoreCase(methodName)) {
				res = methods[i];
				found = true;
			}
			i++;
		}
		return res;
	}
	
	public static boolean hasMethodToDoubleForField(Object o, String fieldName) {
		Method res = findMethodByName(o, fieldName+Data.TO_DOUBLE);
		return res != null && res.getReturnType() == double.class;
	}

	public static List<Field> getCalculableFields(Object object) {
		List<Field> res = new ArrayList<>();
		for (Field field : getFields(object)) {
			field.setAccessible(true);
			if (field.getType() == double.class || hasMethodToDoubleForField(object, field.getName())) {
				res.add(field);
			}
		}
		return res;
	}


	public static double getValueFromFieldByMethod(Object object, String fieldName, Object other) {
		Method fieldToDoubleMethod = findMethodByName(object, fieldName + Data.TO_DOUBLE);
		if (fieldToDoubleMethod != null) {
			fieldToDoubleMethod.setAccessible(true);
			try {
				return (double) fieldToDoubleMethod.invoke(object, other);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Double.MIN_VALUE;
	}
	
	private static double getDoubleFromField(Object object, Field field) throws FieldNotDoubleException {
		if (field.getType() != double.class)
			throw new FieldNotDoubleException();
		
		try {
			field.setAccessible(true);
			return field.getDouble(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public static double getDoubleFromField(Object object, String name) throws FieldNotDoubleException {
		for (Field field : getFields(object)) {
			field.setAccessible(true);
			if (field.getName().equalsIgnoreCase(name)) {
				return getDoubleFromField(object, field);
			}
		}
		return -1;
	}
}
