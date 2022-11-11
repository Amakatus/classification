package app.utils;

import app.exceptions.FieldNotDoubleException;
import app.graphics.models.datas.data.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
		Method res = findMethodByName(o, fieldName + Data.TO_DOUBLE);
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
		if (fieldToDoubleMethod == null) return Double.MIN_VALUE;
		fieldToDoubleMethod.setAccessible(true);
		try {
			return (double) fieldToDoubleMethod.invoke(object, other);
		} catch (Exception e) {
			Logger.exception(e.getMessage());
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
			Logger.exception(e.getMessage());
		}

		return Double.MIN_VALUE;
	}

	public static double getDoubleFromField(Object object, String name) throws FieldNotDoubleException {
		for (Field field : getFields(object)) {
			field.setAccessible(true);
			if (field.getName().equalsIgnoreCase(name)) {
				return getDoubleFromField(object, field);
			}
		}
		return Double.MIN_VALUE;
	}
}
