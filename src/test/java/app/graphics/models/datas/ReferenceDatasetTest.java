package app.graphics.models.datas;

import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceDatasetTest {
    @Test
    void test_generate_deltas() {
        ReferenceDataset<IrisData> rds = new ReferenceDataset<>("rds");
        IrisData iris1 = new IrisData();
        IrisData iris2 = new IrisData();
        IrisData iris3 = new IrisData();
        iris1.setPetalLength(1.1);
        iris2.setPetalLength(2);
        iris3.setPetalLength(3.5);
        rds.addData(iris1, iris2, iris3);
        rds.registerDeltas();
        assertEquals(4, rds.getDeltas().size());
        DataDeltas deltasPetalLength = rds.getDeltas().get("petalLength");
        assertEquals(1.1, deltasPetalLength.getMin());
        assertEquals(3.5, deltasPetalLength.getMax());
        DataDeltas deltasPetalWidth = rds.getDeltas().get("petalWidth");
        assertEquals(0.0, deltasPetalWidth.getMin());
        assertEquals(0.0, deltasPetalWidth.getMax());
    }
}