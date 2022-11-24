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

    static <T extends AbstractData> AbstractGeometry<T> createGeometryAlgorithm(String name, List<String> fieldNames) {
        if (name.equalsIgnoreCase("Euclidean")) {
            return createEuclideanGeometry(fieldNames);
        } else if (name.equalsIgnoreCase("Manhattan")) {
            return createManhattanGeometry(fieldNames);
        }
        // Should use a NullGeometry instead...
        return null;
    }
}