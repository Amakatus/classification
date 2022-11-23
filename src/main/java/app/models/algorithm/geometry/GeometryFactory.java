package app.models.algorithm.geometry;

import app.models.datas.data.AbstractData;

import java.util.List;

public interface GeometryFactory {
    static <T extends AbstractData> ManhattanGeometry<T> createManhattanGeometry(List<String> fieldNames) {
        return new ManhattanGeometry<>(fieldNames);
    }

    static <T extends AbstractData> EuclideanGeometry<T> createEuclideanGeometry(List<String> fieldNames) {
        return new EuclideanGeometry<>(fieldNames);
    }
}