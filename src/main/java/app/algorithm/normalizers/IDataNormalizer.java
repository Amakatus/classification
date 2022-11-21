package app.algorithm.normalizers;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.data.AbstractData;
import app.utils.ClassUtils;
import app.utils.LoggerUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface IDataNormalizer {
    static void normalize(AbstractData data, Map<String, DataDeltas> deltas) {
        List<Field> fields = ClassUtils.getNumberFields(data);
        for (Field field : fields) {
            try {
                double value = field.getDouble(data);
                DataDeltas dataDelta = deltas.get(field.getName());
                field.setDouble(data, dataDelta.getNormalizedValue(value));
            } catch (Exception e) {
                LoggerUtils.exception(e);
            }
        }
    }

    static void denormalize(AbstractData data, Map<String, DataDeltas> deltas) {
        List<Field> fields = ClassUtils.getNumberFields(data);
        for (Field field : fields) {
            try {
                double value = field.getDouble(data);
                DataDeltas dataDelta = deltas.get(field.getName());
                field.setDouble(data, dataDelta.getUnormalizedValue(value));
            } catch (Exception e) {
                LoggerUtils.exception(e);
            }
        }
    }
}
