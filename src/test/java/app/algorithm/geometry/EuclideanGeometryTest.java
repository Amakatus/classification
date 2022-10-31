package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

class EuclideanGeometryTest {
	IrisData wIrisOne;
	IrisData wIrisTwo;
	IrisData rIrisOne;
	IrisData rIrisTwo;
	int kNeighbours;
	ReferenceDataset<IrisData> rDS;
	WorkingDataset<IrisData> wDS;
	KNNAlgorithm<IrisData> algo;
	
	
	@BeforeEach
	void init() {
		wIrisOne = new IrisData();
		wIrisTwo = new IrisData();
		rIrisOne = new IrisData();
		rIrisTwo = new IrisData();
		kNeighbours = 1;
		rDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo));
		wDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo));
		AlgorithmFactory.createAlgorithm(wDS, rDS, kNeighbours);
		algo = wDS.getAlgorithms().get(0);
		wIrisOne.setPetalLength(5);
		rIrisOne.setPetalLength(6);
		wIrisTwo.setPetalLength(10);
		rIrisTwo.setPetalLength(9);
		wIrisOne.setPetalWidth(10);
		rIrisOne.setPetalWidth(25);
		wIrisTwo.setPetalWidth(25);
		rIrisTwo.setPetalWidth(10);
	}
	
	
	@Test
	void test_distance_with_length() {
		assertTrue(algo.getDataWithDistances().isEmpty());
		List<Entry<IrisData, List<IrisData>>> irisDatas = algo.getDatasKNN();
		assertFalse(algo.getDataWithDistances().isEmpty());
		assertEquals(2, algo.getDataWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}
	
	@Test
	void test_key_length() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = algo.getDatasKNN();
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
	}
	
	@Test
	void test_value_length() {
		wDS.addDistanceFieldString("petalLength");
		List<Entry<IrisData, List<IrisData>>> irisDatas = algo.getDatasKNN();
		assertEquals(rIrisOne, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisTwo, irisDatas.get(1).getValue().get(0));
	}
	
	
	@Test
	void test_distance_with_width() {
		wDS.addDistanceFieldString("petalWidth");
		List<Entry<IrisData, List<IrisData>>> irisDatas2 = algo.getDatasKNN();
		assertFalse(algo.getDataWithDistances().isEmpty());
		assertEquals(2, algo.getDataWithDistances().size());
		assertEquals(2, irisDatas2.size());
		assertEquals(kNeighbours, irisDatas2.get(0).getValue().size());
		assertEquals(rIrisTwo, irisDatas2.get(0).getValue().get(0));
		assertEquals(rIrisOne, irisDatas2.get(1).getValue().get(0));
	}
	
	@Test
	void test_key_width() {
		List<Entry<IrisData, List<IrisData>>> irisDatas2 = algo.getDatasKNN();
		assertEquals(wIrisOne, irisDatas2.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas2.get(1).getKey());
	}
	
	

}
