package app.models.algorithm.calculators;

import app.models.algorithm.geometry.GeometryFactory;
import app.models.datas.DatasetFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomDistanceCalculatorTest {
    @Test
    void test_should_get_simple_name() {
        assertEquals("Random", new RandomDistanceCalculator<>(DatasetFactory.irisReferenceDataset("test"), GeometryFactory.createEuclideanGeometry(List.of("test"))).getSimpleName());
    }
}