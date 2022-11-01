package app.graphics.models.datas.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.exceptions.FieldNotDoubleException;

public abstract class Data {
	public static final String TO_DOUBLE = "ToDouble";

	public Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}

	public Method findMethodByName(String methodName) {
		Method res = null;
		Method[] methods = this.getClass().getDeclaredMethods();
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

	public List<Field> getCalculableFields() {
		List<Field> res = new ArrayList<>();
		for (Field field : this.getFields()) {
			if (field.getType() == double.class || this.findMethodByName(field.getName() + Data.TO_DOUBLE) != null) {
				res.add(field);
			}
		}
		return res;
	}

	public double getValueFromField(Field field) throws FieldNotDoubleException {
		if (field.getType() != double.class)
			throw new FieldNotDoubleException();

		try {
			return field.getDouble(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public double getValueFromFieldByMethod(String fieldName, Data other) {
		Method fieldToDoubleMethod = this.findMethodByName(fieldName + Data.TO_DOUBLE);
		if (fieldToDoubleMethod != null) {
			fieldToDoubleMethod.setAccessible(true);
			try {
				return (double) fieldToDoubleMethod.invoke(this, other);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Double.MIN_VALUE;
	}

	public double getValueFromFieldName(String name) throws FieldNotDoubleException {
		for (Field field : this.getFields()) {
			if (field.getName().equalsIgnoreCase(name)) {
				return this.getValueFromField(field);
			}
		}
		return 0;
	}
}
