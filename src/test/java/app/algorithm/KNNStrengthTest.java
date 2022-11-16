package app.algorithm;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KNNStrengthTest {
    protected KNNStrength<IrisData> strength;
    protected KNNAlgorithm<IrisData> algorithm;

    public KNNStrengthTest() {
        IrisData iris = new IrisData();
        ReferenceDataset<IrisData> rDS = new ReferenceDataset<>("rDS");
        WorkingDataset<IrisData> wDS = new WorkingDataset<>("wDS", rDS);
        wDS.addData(iris);
        AlgorithmFactory.createKNN(wDS, 3);
        algorithm = wDS.getAlgorithms().get(0);
        strength = algorithm.getStrengthObject();
    }

    @Test
    public void test_get_algorithm() {
        assertEquals(this.algorithm, this.strength.getAlgorithm());
    }

    @Test
    public void test_get_strength() {
        assertEquals(0.0, this.strength.getStrength());
        assertEquals(this.algorithm.getStrength(), this.strength.getStrength());
    }
}