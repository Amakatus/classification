package app.models.algorithm.calculators;

import app.models.algorithm.geometry.GeometryFactory;
import app.models.datas.DatasetFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistanceCalculatorTest {
    @Test
    void test_should_get_simple_name() {
        assertEquals("Distance", new DistanceCalculator<>(DatasetFactory.irisReferenceDataset("test"), GeometryFactory.createEuclideanGeometry(List.of("test"))).getSimpleName());
    }
}