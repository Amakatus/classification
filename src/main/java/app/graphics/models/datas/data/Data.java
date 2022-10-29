package app.graphics.models.datas.data;

import java.lang.reflect.Field;
import java.util.List;

public abstract class Data {
	public Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}
	
	public List<Field> getCalculableFields() {
		// TODO Auto-generated method stub
		return null;
	}
}
