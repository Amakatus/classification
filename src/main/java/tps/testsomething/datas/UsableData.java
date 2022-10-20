package tps.testsomething.datas;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class UsableData {
	
	public Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}
	
	public List<Field> getAvailableDistanceField() {
		List<Field> res = new ArrayList<>();
		for(Field field : this.getFields()) {
			if(field.getAnnotation(DistanceField.class) != null) {
				res.add(field);
			}
		}
		return res;
	}
}
