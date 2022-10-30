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

class EuclideanGeometryTest {

	@Test
	void testDistance() {
		IrisData wIrisOne = new IrisData();
		IrisData wIrisTwo = new IrisData();
		IrisData rIrisOne = new IrisData();
		IrisData rIrisTwo = new IrisData();
		int kNeighbours = 1;
		
		wIrisOne.setPetalLength(5);
		rIrisOne.setPetalLength(6);
		wIrisTwo.setPetalLength(10);
		rIrisTwo.setPetalLength(9);
		
		ReferenceDataset<IrisData> rDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo));
		WorkingDataset<IrisData> wDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo));
		AlgorithmFactory.createAlgorithm(wDS, rDS, kNeighbours);
		wDS.addDistanceFieldString("petalLength");
		KNNAlgorithm<IrisData> algo = wDS.getAlgorithms().get(0);
		assertTrue(algo.getDataWithDistances().isEmpty());
		List<Entry<IrisData, List<IrisData>>> irisDatas = algo.getDatasKNN();
		assertFalse(algo.getDataWithDistances().isEmpty());
		assertEquals(2, algo.getDataWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
		
		assertEquals(rIrisOne, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisTwo, irisDatas.get(1).getValue().get(0));
	}

}
