package app.models.algorithm.geometry;

import app.exceptions.FieldToDistanceException;
import app.models.datas.data.AbstractData;

import java.util.List;

public class EuclideanGeometry<T extends AbstractData> extends AbstractGeometry<T> {
    public static final int EUCLIDEAN_POWER = 2;

    public EuclideanGeometry(List<String> fieldNames) {
        super(fieldNames);
    }

    @Override
    public double distance(T workingData, T referenceData) throws FieldToDistanceException {
        double sumPow = 0;
        double distance;
        for (String fieldName : this.fieldNames) {
            distance = this.findDistanceForField(workingData, referenceData, fieldName);
            if (distance == Double.MAX_VALUE) throw new FieldToDistanceException(fieldName);
            else sumPow += Math.pow(distance, EUCLIDEAN_POWER);
        }
        return Math.sqrt(sumPow);
    }
}
