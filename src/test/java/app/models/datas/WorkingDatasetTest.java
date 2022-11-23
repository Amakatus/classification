package app.models.datas;

import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkingDatasetTest {
    WorkingDataset<IrisData> workingDS;

    @BeforeEach
    void init() {
        workingDS = new WorkingDataset<IrisData>(null);
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