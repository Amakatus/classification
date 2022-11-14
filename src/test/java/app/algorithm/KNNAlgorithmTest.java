package app.algorithm;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.*;

class KNNAlgorithmTest {
	IrisData wIrisOne;
	IrisData wIrisTwo;
	IrisData rIrisOne;
	IrisData rIrisTwo;
	int kNeighbours;
	ReferenceDataset<IrisData> referenceDS;
	WorkingDataset<IrisData> workingDS;
	KNNAlgorithm<IrisData> knnAlgorithm;
	KNNCalculator<IrisData> calculator;
	
	
	@BeforeEach
	void init() {
		wIrisOne = new IrisData();
		wIrisTwo = new IrisData();
		rIrisOne = new IrisData();
		rIrisTwo = new IrisData();
		kNeighbours = 1;
		referenceDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo));
		workingDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo), referenceDS);
		AlgorithmFactory.createKNN(workingDS, kNeighbours);
		knnAlgorithm = workingDS.getAlgorithms().get(0);
		calculator = knnAlgorithm.getCalculator();
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
	void test_get_strength() {
		this.knnAlgorithm.setStrength(100);
		assertEquals(100, this.knnAlgorithm.getStrength());
	}
	
	@Test
	void test_get_working_dataset() {
		assertEquals(this.workingDS, this.knnAlgorithm.getWorkingDataset());
	}
	
	@Test
	void test_get_kneighbours() {
		assertEquals(1,this.knnAlgorithm.getKNeighbours());
	}
	
	@Test
	void test_calculator_auto_init() {
		assertNotNull(this.knnAlgorithm.getCalculator());
		assertEquals(this.knnAlgorithm, this.knnAlgorithm.getCalculator().getAlgorithm());
	}
	
	@Test
	void test_to_string() {
		assertEquals("1NNAlgorithm (72.0%)", this.knnAlgorithm.toString());
	}
	
	@Test
	void test_distance_with_length() {
		assertTrue(calculator.getDatasWithDistances().isEmpty());
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertFalse(calculator.getDatasWithDistances().isEmpty());
		assertEquals(2, calculator.getDatasWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}
	
	@Test
	void test_key_for_length() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
	}
	
	@Test
	void test_value_length() {
		workingDS.addDistanceFieldString("petalLength");
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertEquals(rIrisOne, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisTwo, irisDatas.get(1).getValue().get(0));
	}
	
	
	@Test
	void test_distance_with_width() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertFalse(calculator.getDatasWithDistances().isEmpty());
		assertEquals(2, calculator.getDatasWithDistances().size());
		assertEquals(2, irisDatas.size());
		assertEquals(kNeighbours, irisDatas.get(0).getValue().size());
	}
	
	@Test
	void test_key_for_width_and_length() {
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertEquals(wIrisOne, irisDatas.get(0).getKey());
		assertEquals(wIrisTwo, irisDatas.get(1).getKey());
	}
	
	@Test
	void test_value_width_and_length() {
		workingDS.addDistanceFieldString("petalLength");
		workingDS.addDistanceFieldString("petalWidth");
		List<Entry<IrisData, List<IrisData>>> irisDatas = knnAlgorithm.getDatasKNN();
		assertEquals(rIrisTwo, irisDatas.get(0).getValue().get(0));
		assertEquals(rIrisOne, irisDatas.get(1).getValue().get(0));
	}
	
	@Test
	void test_only_one_distance_filed_name_was_add () {
		workingDS.addDistanceFieldString("petalWidth");
		workingDS.addDistanceFieldString("petalWidth");
		assertEquals(1,workingDS.getDistanceFields().size());
	}
}
