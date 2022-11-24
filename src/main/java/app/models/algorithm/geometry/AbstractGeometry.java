package app.models.algorithm.geometry;

import app.exceptions.FieldNotNumberException;
import app.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.List;


public abstract class AbstractGeometry<T extends AbstractData> implements IGeometry<T> {
    protected List<String> fieldNames;

    protected AbstractGeometry(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    protected double findDistanceForField(T workingData, T referenceData, String fieldName) {
        try {
            return ClassUtils.getDoubleFromField(workingData, fieldName) - ClassUtils.getDoubleFromField(referenceData, fieldName);
        } catch (FieldNotNumberException exception) {
            double fromMethod = ClassUtils.getValueFromFieldByMethod(workingData, fieldName, referenceData);
            if (fromMethod != Double.MAX_VALUE) {
                return fromMethod;
            } else {
                Object wValue = ClassUtils.getValueObjectFromField(workingData, fieldName);
                Object rValue = ClassUtils.getValueObjectFromField(referenceData, fieldName);
                if (wValue == null || rValue == null) return Double.MAX_VALUE;
                return GeneralToDouble.toDouble(wValue, rValue);
            }
        }
    }

    public String toString() {
        return this.getClass().getSimpleName().charAt(0) + " ";
    }
}
