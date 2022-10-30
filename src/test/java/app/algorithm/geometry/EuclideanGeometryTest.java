package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.utils.CSVUtils;

class EuclideanGeometryTest {

	@Test
	void testDistance() {
		IrisData irisOne = new IrisData();
		IrisData irisTwo = new IrisData();
		int kNeighbours = 3;
		
		irisOne.setPetalLength(5);
		irisTwo.setPetalLength(10);
		
		ReferenceDataset<IrisData> rDS = new ReferenceDataset<IrisData>("rDS", CSVUtils.loadIrisCSV());
		WorkingDataset<IrisData> wDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(irisOne, irisTwo));
		AlgorithmFactory.createAlgorithm(wDS, rDS, kNeighbours);
		wDS.addDistanceFieldString("petalLength");
		KNNAlgorithm<IrisData> algo = wDS.getAlgorithms().get(0);
		assertTrue(algo.getDataWithDistances().isEmpty());
		List<Entry<IrisData, List<IrisData>>> irisDatas = algo.getDatasKNN();
		assertFalse(algo.getDataWithDistances().isEmpty());
		assertEquals(2, algo.getDataWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(irisOne, irisDatas.get(0).getKey());
		assertEquals(irisTwo, irisDatas.get(1).getKey());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}

}
