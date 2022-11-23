package app.algorithm;

import app.graphics.models.datas.DatasetClassifier;
import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KNNStrengthTest {
    protected KNNStrength<IrisData> strength;
    protected KNNAlgorithm<IrisData> algorithm;
    protected ReferenceDataset<IrisData> rDS;
    protected WorkingDataset<IrisData> wDS;
    protected KNNAlgorithm<IrisData> algo;
    protected DatasetClassifier<IrisData> classifier;

    public KNNStrengthTest() {
        wDS = DatasetFactory.createWorkingDataset("wDS", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
        wDS.setCategoryField("variety");
        wDS.addDistanceFieldString("petalLength");
        wDS.addDistanceFieldString("petalWidth");
        AlgorithmFactory.createKNN(wDS, 1);
        algo = wDS.getLastAlgorithm();
        strength = algo.getStrengthObject();
        strength.calculStrenght();
    }

    @Test
    void test_get_groups_size() {
        assertEquals(30, this.strength.getGroupsToTest().size());
    }

    @Test
    void test_strength_is_consistent() {
        for (int i = 0; i < 10; i++) {
            strength.calculStrenght();
            assertTrue(strength.getStrength() >= 0 && strength.getStrength() <= 100);
        }
    }
}