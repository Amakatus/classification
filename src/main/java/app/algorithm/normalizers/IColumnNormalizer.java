package app.algorithm.normalizers;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.AbstractData;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface IColumnNormalizer {
    List<Field> normalize(AbstractData data, Map<String, DataDeltas> deltas);

    void denormalize(AbstractData data, Map<String, DataDeltas> deltas);
}
