package app.graphics.models.datas.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.annotations.CalculableField;

public abstract class Data {
	public Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}
	
	public Method findMethodByName(String methodName) {
		Method res = null;
		Method[] methods = this.getClass().getDeclaredMethods();
		boolean found = false;
		int i = 0;
		while(!found && i < methods.length) {
			if(methods[i].getName().equalsIgnoreCase(methodName)) {
				res = methods[i];
				found = true;
			}
			i++;
		}
		return res;
	}
	
	public List<Field> getCalculableFields() {
		List<Field> res = new ArrayList<>();
		for(Field field : this.getFields()) {
			if(field.getAnnotation(CalculableField.class) != null) {
				res.add(field);
			}
		}
		return res;
	}
	
	public double getValueFromField(Field field) {
		if(field.getType() != double.class)
			return getValueFromFieldByMethod(field);
		
		try {
			return field.getDouble(this);
		} catch (Exception e) { e.printStackTrace(); }
		return -1;
	}

	private double getValueFromFieldByMethod(Field field) {
		Method fieldToDoubleMethod = this.findMethodByName(field.getName()+"ToDouble");
		if(fieldToDoubleMethod != null) {
			fieldToDoubleMethod.setAccessible(true);
			try {
				return (double) fieldToDoubleMethod.invoke(this);
			} catch (Exception e) {e.printStackTrace(); }
		}
		return -1;
	}
	
	public double getValueFromFieldName(String name) {
		for(Field field : this.getCalculableFields()) {
			if(field.getName().equalsIgnoreCase(name)) {
				return this.getValueFromField(field);
			}
		}
		return -1;
	}
}
