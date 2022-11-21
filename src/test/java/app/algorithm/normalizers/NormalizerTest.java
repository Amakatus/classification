package app.algorithm.normalizers;

import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalizerTest {
    ReferenceDataset<IrisData> rds;
    IrisData iris1 = new IrisData();
    IrisData iris2 = new IrisData();
    IrisData iris3 = new IrisData();
    IrisData iris4 = new IrisData();
    IrisData iris5 = new IrisData();
    int kNeighbours;
    WorkingDataset<IrisData> workingDS;
    DataDeltas deltasPetalLength;

    @BeforeEach
    void init() {
        rds = new ReferenceDataset<>("rds");
        kNeighbours = 1;
        iris1.setPetalLength(5.0);
        iris2.setPetalLength(6.0);
        iris3.setPetalLength(10.0);
        iris4.setPetalLength(8.0);
        iris5.setPetalLength(7.0);
        iris1.setPetalWidth(0.0);
        iris2.setPetalWidth(5.0);
        iris3.setPetalWidth(10.0);
        rds.addData(iris1, iris2, iris3);
        rds.registerDeltas();
        deltasPetalLength = rds.getDeltas().get("petalLength");
    }

    @Test
    void test_reference_dataset_values_of_iris_should_be_between_0_and_1_after_normalize() {
        Map<String, DataDeltas> deltaOfDataset = rds.getDeltas();
        for (IrisData data : rds.getDatas()) {
            IDataNormalizer.normalize(data, deltaOfDataset);
        }
        assertEquals(0.0, rds.getDatas().get(0).getPetalLength());
        assertEquals(0.2, rds.getDatas().get(1).getPetalLength());
        assertEquals(1.0, rds.getDatas().get(2).getPetalLength());
        assertEquals(0.0, rds.getDatas().get(0).getPetalWidth());
        assertEquals(0.5, rds.getDatas().get(1).getPetalWidth());
        assertEquals(1.0, rds.getDatas().get(2).getPetalWidth());
    }

    @Test
    void test_reference_dataset_values_of_iris_should_be_return_to_initial_values() {
        Map<String, DataDeltas> deltaOfDataset = rds.getDeltas();

        for (IrisData data : rds.getDatas()) {
            IDataNormalizer.normalize(data, deltaOfDataset);
        }
        for (IrisData data : rds.getDatas()) {
            IDataNormalizer.denormalize(data, deltaOfDataset);
        }
        assertEquals(5.0, rds.getDatas().get(0).getPetalLength());
        assertEquals(6.0, rds.getDatas().get(1).getPetalLength());
        assertEquals(10.0, rds.getDatas().get(2).getPetalLength());
        assertEquals(0.0, rds.getDatas().get(0).getPetalWidth());
        assertEquals(5.0, rds.getDatas().get(1).getPetalWidth());
        assertEquals(10.0, rds.getDatas().get(2).getPetalWidth());
    }

    @Test
    void test_working_dataset_values_of_iris_should_be_between_0_and_1_after_normalize() {
        workingDS = new WorkingDataset<>("Iris", rds);
        workingDS.addData(iris4, iris5);
        workingDS.unNormalizeDatas();
        workingDS.normalizeDatas();
        assertEquals(0.6, workingDS.getDatas().get(0).getPetalLength());
        assertEquals(0.4, workingDS.getDatas().get(1).getPetalLength());
    }

    @Test
    void test_working_dataset_values_of_iris_should_be_return_to_initial_values() {
        workingDS = new WorkingDataset<>("Iris", rds);
        workingDS.addData(iris4, iris5);
        workingDS.unNormalizeDatas();
        workingDS.normalizeDatas();
        workingDS.unNormalizeDatas();
        assertEquals(8.0, workingDS.getDatas().get(0).getPetalLength());
        assertEquals(7.0, workingDS.getDatas().get(1).getPetalLength());
    }

}
