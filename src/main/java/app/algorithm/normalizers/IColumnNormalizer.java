package app.algorithm.normalizers;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.AbstractData;

import java.util.Map;

public interface IColumnNormalizer {
    void normalize(AbstractData data, Map<String, DataDeltas> deltas);

    void denormalize(AbstractData data, Map<String, DataDeltas> deltas);
}
