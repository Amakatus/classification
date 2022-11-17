package app.algorithm.normalizers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.Data;

public interface IColumnNormalizer {
	List<Field> normalize(Data data, Map<String, DataDeltas> deltas);
	void denormalize(Data data, Map<String, DataDeltas> deltas);
}
