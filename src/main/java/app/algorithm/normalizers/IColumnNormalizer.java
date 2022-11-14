package app.algorithm.normalizers;

import java.util.Map;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.Data;

public interface IColumnNormalizer {
	void normalize(Data data, Map<String, DataDeltas> deltas);
	Object denormalize(double value);
}
