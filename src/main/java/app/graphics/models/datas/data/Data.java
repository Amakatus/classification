package app.graphics.models.datas.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import app.annotations.CalculableField;

public abstract class Data {
	public Field[] getFields() {
		return this.getClass().getDeclaredFields();
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
		try {
			return field.getDouble(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double getValueFromFieldName(String name) {
		for(Field field : this.getCalculableFields()) {
			if(field.getName().equalsIgnoreCase(name)) {
				return this.getValueFromField(field);
			}
		}
		return 0;
	}
}
