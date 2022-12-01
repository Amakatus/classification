package app.models.datas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.models.datas.data.IrisVariety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.controllers.IndexController;
import app.models.algorithm.KNNAlgorithm;
import app.models.algorithm.calculators.DistanceCalculator;
import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.utils.ProjectUtils;

class WorkingDatasetTest {
	WorkingDataset<IrisData> workingDS;
	ReferenceDataset<IrisData> rd;

	@BeforeEach
	void init() {
		workingDS = new WorkingDataset<IrisData>(rd);
	}

	@Test
	void test_workingDataset_should_be_able_to_attach_observer() {
		assertTrue(workingDS.observers.isEmpty());
		workingDS.attach(new IndexController());
		assertEquals(1, workingDS.observers.size());
	}

	@Test
	void test_workingDataset_should_be_able_to_detach_observer() {
		assertTrue(workingDS.observers.isEmpty());
		IndexController ic = new IndexController();
		workingDS.attach(ic);
		assertEquals(1, workingDS.observers.size());
		workingDS.detach(ic);
		assertEquals(0, workingDS.observers.size());
	}

	@Test
	void should_remove_data() {
		IrisData irisTest = new IrisData();
		workingDS.addData(irisTest);
		assertEquals(1, workingDS.getDataSize());
		workingDS.removeData(irisTest);
		assertEquals(0, workingDS.getDataSize());
	}

	@Test
	void should_add_list_of_data() {
		IrisData irisTest = new IrisData();
		IrisData irisTest2 = new IrisData();
		List<IrisData> listIris = new ArrayList<>();
		listIris.add(irisTest);
		listIris.add(irisTest2);
		workingDS.setData(listIris);
		assertEquals(listIris, workingDS.getData());
	}

	@Test
	void should_return_data_by_category() {
		IrisData irisTest = new IrisData();
		IrisData irisTest2 = new IrisData();
		irisTest.setPetalLength(5);
		irisTest2.setPetalLength(6);
		workingDS.addData(irisTest);
		workingDS.addData(irisTest2);
		workingDS.setCategoryField("petalLength");
		assertEquals(workingDS.getDataSize(), workingDS.getWorkingDataByCategories().size());
	}

	@Test
	void should_return_data_by_categorie_when_category_is_null() {
		IrisData irisTest = new IrisData();
		IrisData irisTest2 = new IrisData();
		irisTest.setPetalLength(5);
		irisTest2.setPetalLength(6);
		workingDS.addData(irisTest);
		workingDS.addData(irisTest2);
		assertEquals(0, workingDS.getDataByCategories("").size());
	}

	@Test
	void test_to_string() {
		WorkingDataset wd = new WorkingDataset<>("Cartes Graphiques", null, null);
		assertEquals("Cartes Graphiques", wd.toString());
	}
	
	@Test
	void should_create_KNNalgorithm() {
		workingDS.createKNN(5, false, new DistanceCalculator<IrisData>(null, null));
		assertNotEquals(null,workingDS.getAlgorithms());
	}


	@Test
	void test_setter_and_getter_categoryField() {
		workingDS.setCategoryField("petalLength");
		assertEquals("petalLength", workingDS.getCategoryField());
	}

	@Test
	void test_should_not_add_distance_field_already_existing() {
		assertEquals(0, workingDS.getDistanceFields().size());
		workingDS.addDistanceFieldString("petal");
		assertEquals(1, workingDS.getDistanceFields().size());
		workingDS.addDistanceFieldString("petal");
		assertEquals(1, workingDS.getDistanceFields().size());
	}

	@Test
	void test_create_algorithm() {
		assertEquals(0, workingDS.getAlgorithms().size());
		workingDS.createKNN(5);
		assertEquals(1, workingDS.getAlgorithms().size());
		workingDS.createKNN(5);
		workingDS.createKNN(3);
		assertEquals(3, workingDS.getAlgorithms().size());
	}

	@Test
	void test_dataset_cant_change_normalize() {
		assertFalse(workingDS.normalizeDatas());
		assertFalse(workingDS.isNormalized());
		assertFalse(workingDS.unNormalizeDatas());
		workingDS.addData(new IrisData());
		workingDS.setReferenceDataset(DatasetFactory.irisReferenceDataset("test"));
		assertTrue(workingDS.normalizeDatas());
		assertTrue(workingDS.isNormalized());
		assertFalse(workingDS.normalizeDatas());
		assertTrue(workingDS.unNormalizeDatas());
		assertFalse(workingDS.isNormalized());
		assertFalse(workingDS.unNormalizeDatas());
	}

	@Test
	void test_dataset_can_change_normalize() {
		WorkingDataset<IrisData> workingDataset2 = new WorkingDataset<>(List.of(new IrisData()),
				DatasetFactory.irisReferenceDataset("Test"));
		assertFalse(workingDataset2.isNormalized());
		workingDataset2.normalizeDatas();
		assertTrue(workingDataset2.isNormalized());
	}

	@Test
	void test_dataset_auto_normalize_data_if_normalized() {
		WorkingDataset<IrisData> workingDataset2 = DatasetFactory.createWorkingDataset("test", DataType.IRIS,
				ProjectUtils.getFile("/data/iris.csv"));
		IrisData irisTest = new IrisData();
		irisTest.setPetalLength(2);
		assertEquals(2, irisTest.getPetalLength());
		workingDataset2.normalizeDatas();
		workingDataset2.addData(irisTest);
		assertTrue(irisTest.getPetalLength() >= 0 && irisTest.getPetalLength() <= 1);
	}

	@Test
	void test_dataset_not_auto_normalize_data_if_normalized() {
		WorkingDataset<IrisData> workingDataset2 = DatasetFactory.createWorkingDataset("test", DataType.IRIS,
				ProjectUtils.getFile("/data/iris.csv"));
		workingDataset2.unNormalizeDatas();
		IrisData irisTest = new IrisData();
		irisTest.setPetalLength(2);
		assertEquals(2, irisTest.getPetalLength());
		workingDataset2.addData(irisTest);
		assertEquals(2, irisTest.getPetalLength());
	}

	@Test
	void test_get_data_by_categories() {
		WorkingDataset<IrisData> workingDataset2 = DatasetFactory.createWorkingDataset("test", DataType.IRIS,
				ProjectUtils.getFile("/data/iris.csv"));
		Map<String, List<IrisData>> dataByCategories = workingDataset2.getBothDataByCategories();
		for(Map.Entry<String, List<IrisData>> dataCatEntry : dataByCategories.entrySet()){
			assertEquals(100, dataCatEntry.getValue().size());
			assertTrue(List.of(IrisVariety.values()).contains(dataCatEntry.getKey()));
		}
	}
}