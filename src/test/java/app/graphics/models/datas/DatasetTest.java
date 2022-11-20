package app.graphics.models.datas;

import app.algorithm.AlgorithmFactory;
import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatasetTest<T> {
    WorkingDataset<IrisData> testDataset = null;

    @BeforeEach
    void initDataset() {
        this.testDataset = new WorkingDataset<>("testDataset", new ReferenceDataset<IrisData>("WorkingDS"));
    }

    @Test
    void testGetTitle() {
        assertEquals("testDataset", this.testDataset.getTitle());
    }

    @Test
    void testSetDatas() {
        assertTrue(this.testDataset.getDatas().isEmpty());
        List<IrisData> datas = new ArrayList<>();
        datas.add(new IrisData());
        datas.add(new IrisData());
        this.testDataset.setDatas(datas);
        assertEquals(2, this.testDataset.getDatas().size());
    }

    @Test
    void testAddData() {
        assertTrue(this.testDataset.getDatas().isEmpty());
        this.testDataset.addData(new IrisData());
        assertEquals(1, this.testDataset.getDatas().size());
        this.testDataset.addData(new IrisData(), new IrisData());
        assertEquals(3, this.testDataset.getDatas().size());
    }

    @Test
    void testRemoveData() {
        assertTrue(this.testDataset.getDatas().isEmpty());
        this.testDataset.addData(new IrisData());
        this.testDataset.removeData(testDataset.getDatas().get(0));
        assertEquals(0, this.testDataset.getDatas().size());
        this.testDataset.addData(new IrisData(), new IrisData());
        assertEquals(2, this.testDataset.getDatas().size());
        this.testDataset.removeData(testDataset.getDatas().get(0), testDataset.getDatas().get(1));
        assertEquals(0, this.testDataset.getDatas().size());
    }

    @Test
    void testClearData() {
        this.testDataset.addData(new IrisData());
        this.testDataset.clearData();
        assertEquals(0, this.testDataset.getDatas().size());
    }

    @Test
    void testGetAlgorithms() {
        assertTrue(this.testDataset.getAlgorithms().isEmpty());
    }

    @Test
    void testAddKNNAlgorithm() {
        assertTrue(this.testDataset.getAlgorithms().isEmpty());
        AlgorithmFactory.createKNN(this.testDataset, 5);
        assertEquals(1, this.testDataset.getAlgorithms().size());
    }


    @Test
    void testToString() {
        assertEquals("testDataset", this.testDataset.toString());
    }

}
