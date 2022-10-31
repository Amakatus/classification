package app.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

class KNNAlgorithmTest {
	IrisData wIrisOne;
	IrisData wIrisTwo;
	IrisData rIrisOne;
	IrisData rIrisTwo;
	int kNeighbours;
	ReferenceDataset<IrisData> referenceDS;
	WorkingDataset<IrisData> workingDS;
	KNNAlgorithm<IrisData> euclideanGeometry;
	
	
	@BeforeEach
	void init() {
		wIrisOne = new IrisData();
		wIrisTwo = new IrisData();
		rIrisOne = new IrisData();
		rIrisTwo = new IrisData();
		kNeighbours = 1;
		referenceDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo));
		workingDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo), referenceDS);
		AlgorithmFactory.createAlgorithm(workingDS, kNeighbours);
		euclideanGeometry = workingDS.getAlgorithms().get(0);
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
		assertTrue(euclideanGeometry.getDataWithDistances().isEmpty());
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertFalse(euclideanGeometry.getDataWithDistances().isEmpty());
		assertEquals(2, euclideanGeometry.getDataWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}
	
	@Test
	void test_key_for_length() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
	}
	
	@Test
	void test_value_length() {
		workingDS.addDistanceFieldString("petalLength");
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertEquals(rIrisOne, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisTwo, irisDatas.get(1).getValue().get(0));
	}
	
	
	@Test
	void test_distance_with_width() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertFalse(euclideanGeometry.getDataWithDistances().isEmpty());
		assertEquals(2, euclideanGeometry.getDataWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}
	
	@Test
	void test_key_for_width_and_length() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
	}
	
	@Test
	void test_value_width_and_length() {
		workingDS.addDistanceFieldString("petalLength");
		workingDS.addDistanceFieldString("petalWidth");
		List<Entry<IrisData, List<IrisData>>> irisDatas = euclideanGeometry.getDatasKNN();
		assertEquals(rIrisTwo, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisOne, irisDatas.get(1).getValue().get(0));
	}
}
