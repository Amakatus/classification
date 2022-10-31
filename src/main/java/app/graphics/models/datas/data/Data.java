package app.graphics.models.datas.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.annotations.CalculableField;
import app.exceptions.RequestedFieldNotDouble;

public abstract class Data {
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
			if (field.getAnnotation(CalculableField.class) != null) {
				res.add(field);
			}
		}
		return res;
	}

	public double getValueFromField(Field field) throws RequestedFieldNotDouble {
		if (field.getType() != double.class)
			throw new RequestedFieldNotDouble();

		try {
			return field.getDouble(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getValueFromFieldByMethod(String fieldName, Data other) {
		Method fieldToDoubleMethod = this.findMethodByName(fieldName+"ToDouble");
		if(fieldToDoubleMethod != null) {
			fieldToDoubleMethod.setAccessible(true);
			try {
				return (double) fieldToDoubleMethod.invoke(this, other);
			} catch (Exception e) { e.printStackTrace(); }
		}
		return -1;
	}


	public double getValueFromFieldName(String name) throws RequestedFieldNotDouble {
		for (Field field : this.getCalculableFields()) {
			if (field.getName().equalsIgnoreCase(name)) {
				return this.getValueFromField(field);
			}
		}
		return 0;
	}
}
