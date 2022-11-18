package app.graphics.models.datas;

import app.exceptions.FieldNotNumberException;
import app.graphics.models.datas.data.AbstractData;
import app.utils.ClassUtils;
import app.utils.Logger;

import java.lang.reflect.Field;
import java.util.*;

public class ReferenceDataset<T extends AbstractData> extends Dataset<T> {
    protected Map<String, DataDeltas> fieldsDeltas;

    public ReferenceDataset(String title, List<T> datas) {
        super(title, datas);
        this.fieldsDeltas = new HashMap<>();
        this.registerDeltas();
    }

    public ReferenceDataset(String title) {
        this(title, null);
    }

    public void registerDeltas() {
        if (!this.datas.isEmpty()) {
            ArrayList<Field> numberFields = (ArrayList<Field>) ClassUtils.getNumberFields(this.datas.get(0));
            ArrayList<Double> values = new ArrayList<>();
            for (Field field : numberFields) {
                for (T data : this.datas) {
                    try {
                        values.add(ClassUtils.getDoubleFromField(data, field.getName()));
                    } catch (FieldNotNumberException e) {
                        Logger.exception(e);
                    }
                }
                this.fieldsDeltas.put(field.getName(), new DataDeltas(Collections.min(values), Collections.max(values)));
                values.clear();
            }
        }
    }

    public Map<String, DataDeltas> getDeltas() {
        return this.fieldsDeltas;
    }
}