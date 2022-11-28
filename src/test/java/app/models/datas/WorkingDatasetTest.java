package app.models.datas;

import app.controllers.IndexController;
import app.models.algorithm.KNNAlgorithm;
import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorkingDatasetTest {
    WorkingDataset<IrisData> workingDS;
   
    
    @BeforeEach
    void init() {
        workingDS = new WorkingDataset<IrisData>(null);
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
         assertEquals(1,workingDS.getDataSize());
         workingDS.removeData(irisTest);
         assertEquals(0,workingDS.getDataSize());
    }
    
    @Test
    void should_add_list_of_data() {
    	IrisData irisTest = new IrisData();
    	IrisData irisTest2 = new IrisData();
    	List<IrisData> listIris = new ArrayList<>();
    	listIris.add(irisTest);
    	listIris.add(irisTest2);
    	workingDS.setData(listIris);
    	assertEquals(listIris,workingDS.getData());
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
    	assertEquals(workingDS.getDataSize(),workingDS.getWorkingDataByCategories().size());
    }
    
    @Test
    void should_return_data_by_categorie_when_category_is_null() {
    	IrisData irisTest = new IrisData();
    	IrisData irisTest2 = new IrisData();
    	irisTest.setPetalLength(5);
    	irisTest2.setPetalLength(6);
    	workingDS.addData(irisTest);
    	workingDS.addData(irisTest2);
    	assertEquals(0,workingDS.getDataByCategories("").size());
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
        workingDS.normalizeDatas();
        assertFalse(workingDS.isNormalized());
    }

    @Test
    void test_dataset_can_change_normalize() {
        WorkingDataset<IrisData> workingDataset2 = new WorkingDataset<>(List.of(new IrisData()), DatasetFactory.irisReferenceDataset("Test"));
        assertTrue(workingDataset2.isNormalized());
        workingDataset2.unNormalizeDatas();
        assertFalse(workingDataset2.isNormalized());
    }

    @Test
    void test_dataset_auto_normalize_data_if_normalized() {
        WorkingDataset<IrisData> workingDataset2 = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
        IrisData irisTest = new IrisData();
        irisTest.setPetalLength(2);
        assertEquals(2, irisTest.getPetalLength());
        workingDataset2.addData(irisTest);
        assertTrue(irisTest.getPetalLength() >= 0 && irisTest.getPetalLength() <= 1);
    }

    @Test
    void test_dataset_not_auto_normalize_data_if_normalized() {
        WorkingDataset<IrisData> workingDataset2 = DatasetFactory.createWorkingDataset("test", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
        workingDataset2.unNormalizeDatas();
        IrisData irisTest = new IrisData();
        irisTest.setPetalLength(2);
        assertEquals(2, irisTest.getPetalLength());
        workingDataset2.addData(irisTest);
        assertEquals(2, irisTest.getPetalLength());
    }
}