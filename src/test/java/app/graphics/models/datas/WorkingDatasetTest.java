package app.graphics.models.datas;

import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkingDatasetTest {
    WorkingDataset<IrisData> workingDS;

    @BeforeEach
    void init() {
        workingDS = new WorkingDataset<IrisData>("wDS", null);
    }

    @Test
    void test_setter_and_getter_categoryField() {
        workingDS.setCategoryField("petalLength");
        assertEquals("petalLength", workingDS.getCategoryField());
    }

    @Test
    void test_create_algorithm() {
        assertEquals(0, workingDS.getAlgorithms().size());
        workingDS.createAlgorithm(5);
        assertEquals(1, workingDS.getAlgorithms().size());
        workingDS.createAlgorithm(5);
        workingDS.createAlgorithm(3);
        assertEquals(3, workingDS.getAlgorithms().size());
    }
}
