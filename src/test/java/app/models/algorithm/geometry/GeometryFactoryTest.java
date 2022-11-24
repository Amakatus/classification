package app.models.algorithm.geometry;

import app.models.datas.DatasetFactory;
import app.models.datas.WorkingDataset;
import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeometryFactoryTest {
    WorkingDataset<IrisData> workingData;
    AbstractGeometry<IrisData> geometry;

    public GeometryFactoryTest() {
        workingData = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
        workingData.createKNN(2);
        geometry = null;
    }

    @Test
    void should_create_a_euclideangeometry_with_factory() {
        geometry = GeometryFactory.createEuclideanGeometry(workingData.getLastAlgorithm().getWorkingDataset().getDistanceFields());
        assertNotNull(geometry);
    }

    @Test
    void should_create_a_manhatthangeometry_with_factory() {
        geometry = GeometryFactory.createManhattanGeometry(workingData.getLastAlgorithm().getWorkingDataset().getDistanceFields());
        assertNotNull(geometry);
    }

    @Test
    void should_create_a_manhattangeometry_with_factory_and_name() {
        geometry = GeometryFactory.createGeometryAlgorithm("Manhattan", workingData.getLastAlgorithm().getWorkingDataset().getDistanceFields());
        assertNotNull(geometry);
    }

    @Test
    void should_create_a_euclideangeometry_with_factory_and_name() {
        geometry = GeometryFactory.createGeometryAlgorithm("Euclidean", workingData.getLastAlgorithm().getWorkingDataset().getDistanceFields());
        assertNotNull(geometry);
    }

    @Test
    void should_create_a_null_with_factory_and_name() {
        geometry = GeometryFactory.createGeometryAlgorithm("Euclideean", workingData.getLastAlgorithm().getWorkingDataset().getDistanceFields());
        assertNull(geometry);
    }
}