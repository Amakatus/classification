package app.algorithm.geometry;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GeometryFactoryTest {
    WorkingDataset<IrisData> workingData = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
    AbstractGeometryCalculator<IrisData> geometry;

    @Test
    void should_create_a_euclideangeometry_with_factory() {
        workingData.createAlgorithm(2);
        KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
        geometry = GeometryFactory.createEuclideanGeometry(algorithm.getCalculator());
        assertNotEquals(null, geometry);
    }

    @Test
    void should_create_a_manhatthangeometry_with_factory() {
        workingData.createAlgorithm(2);
        KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
        geometry = GeometryFactory.createManhattanGeometry(algorithm.getCalculator());
        assertNotEquals(null, geometry);
    }
    
    @Test
    void should_create_a_manhattangeometry_with_factory_and_name() {
        workingData.createAlgorithm(2);
        KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
        geometry = GeometryFactory.createGeometryAlgorithm("Manhattan",algorithm.getCalculator());
        assertNotEquals(null, geometry);
    }
    
    @Test
    void should_create_a_euclideangeometry_with_factory_and_name() {
    	workingData.createAlgorithm(2);
        KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
        geometry = GeometryFactory.createGeometryAlgorithm("Euclidean",algorithm.getCalculator());
        assertNotEquals(null, geometry);
    }
}
