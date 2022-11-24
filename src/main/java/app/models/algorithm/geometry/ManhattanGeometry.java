package app.models.algorithm.geometry;

import app.exceptions.FieldToDistanceException;
import app.models.datas.data.AbstractData;

import java.util.List;

public class ManhattanGeometry<T extends AbstractData> extends AbstractGeometry<T> {

    public ManhattanGeometry(List<String> fieldNames) {
        super(fieldNames);
    }

    @Override
    public double distance(T workingData, T referenceData) throws FieldToDistanceException {
        double sum = 0;
        double distance;
        for (String fieldName : this.fieldNames) {
            distance = this.findDistanceForField(workingData, referenceData, fieldName);
            if (distance == Double.MAX_VALUE) throw new FieldToDistanceException(fieldName);
            else sum += Math.abs(distance);
        }
        return sum;
    }
}
