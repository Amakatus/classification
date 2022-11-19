package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.utils.ProjectUtils;

class GeometryFactoryTest {

	@Test
	void should_create_a_euclideangeometry_with_factory() {
		 WorkingDataset<IrisData> workingData = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
		 workingData.createAlgorithm(2);
		 KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
		 EuclideanGeometry<IrisData> mg;
		 mg = GeometryFactory.createEuclideanGeometry(algorithm.getCalculator());
		 assertNotEquals(null,mg);
	}

	@Test
	void should_create_a_manhatthangeometry_with_factory() {
		WorkingDataset<IrisData> workingData = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
		 workingData.createAlgorithm(2);
		 KNNAlgorithm<IrisData> algorithm = workingData.getAlgorithms().get(0);
		 ManhatthanGeometry<IrisData> mg;
		 mg = GeometryFactory.createManhattanGeometry(algorithm.getCalculator());
		 assertNotEquals(null,mg);
	}
}
