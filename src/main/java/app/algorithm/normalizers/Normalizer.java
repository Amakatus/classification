package app.algorithm.normalizers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.IrisData;
import app.utils.ClassUtils;
import app.utils.Logger;

public class Normalizer implements IColumnNormalizer {

	@Override
	public List<Field> normalize(Data data, Map<String, DataDeltas> deltas) {
		List<Field> fields = ClassUtils.getNumberFields(data);
		for (Field field : fields) {
			try {
				double valueNotNormalized = field.getDouble(data);
				double valueNormalized = 0;
				DataDeltas dataDelta = deltas.get(field.getName());
				double min = dataDelta.getMin();
				double delta = dataDelta.getDelta();
				valueNormalized = (valueNotNormalized - min) / delta;
				field.setDouble(data, valueNormalized);
			} catch (Exception e) {
				Logger.exception(e);
			}
		}
		return fields;
	}

	@Override
	public void denormalize(Data data, Map<String, DataDeltas> deltas) {
		List<Field> fields = ClassUtils.getNumberFields(data);
		for (Field field : fields) {
			try {
				double valueNotNormalized = field.getDouble(data);
				double valueNormalized = 0;
				DataDeltas dataDelta = deltas.get(field.getName());
				double min = dataDelta.getMin();
				double delta = dataDelta.getDelta();
				valueNormalized = valueNotNormalized * delta + min;
				field.setDouble(data, valueNormalized);
			} catch (Exception e) {
				Logger.exception(e);
			}
		}
	}

}
