package app.models.datas;

import app.models.datas.data.IrisData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceDatasetTest {
    ReferenceDataset<IrisData> rds = new ReferenceDataset<>("rds");
    IrisData iris1 = new IrisData();
    IrisData iris2 = new IrisData();
    IrisData iris3 = new IrisData();
    DataDeltas deltasPetalLength;

    @BeforeEach
    void init() {
        iris1.setPetalLength(2.5);
        iris2.setPetalLength(1.1);
        iris3.setPetalLength(3.5);
        rds.addData(iris1);
        rds.addData(iris2);
        rds.addData(iris3);
        rds.registerDeltas();
        deltasPetalLength = rds.getDeltas().get("petalLength");
    }

    @Test
    void test_generate_deltas_should_have_entries() {
        assertEquals(4, rds.getDeltas().size());
    }

    @Test
    void test_generate_deltas_should_have_min_and_max() {
        assertEquals(1.1, deltasPetalLength.getMin());
        assertEquals(3.5, deltasPetalLength.getMax());
    }

    @Test
    void test_generate_deltas_should_have_delta() {
        assertEquals(2.4, deltasPetalLength.getDelta());
    }

    @Test
    void test_generate_deltas_should_have_no_min_and_max() {
        DataDeltas deltasPetalWidth = rds.getDeltas().get("petalWidth");
        assertEquals(0.0, deltasPetalWidth.getMin());
        assertEquals(0.0, deltasPetalWidth.getMax());
    }

}