package app.models.algorithm.calculators;

import app.models.algorithm.AlgorithmFactory;
import app.models.algorithm.KNNAlgorithm;
import app.models.datas.DatasetFactory;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrengthCalculatorTest {
    protected StrengthCalculator<IrisData> strength;
    protected KNNAlgorithm<IrisData> algorithm;
    protected ReferenceDataset<IrisData> rDS;
    protected WorkingDataset<IrisData> wDS;
    protected KNNAlgorithm<IrisData> algo;

    public StrengthCalculatorTest() {
        wDS = DatasetFactory.createWorkingDataset("wDS", DataType.IRIS, ProjectUtils.getFile("/data/iris.csv"));
        wDS.setCategoryField("variety");
        wDS.addDistanceFieldString("petalLength");
        wDS.addDistanceFieldString("petalWidth");
        algo = wDS.createKNN(3);
        strength = algo.getStrengthCalculator();
        strength.calculStrenght();
    }

    @Test
    void test_get_groups_size() {
        assertEquals(50, this.strength.getGroupsToTest().size());
    }

    @Test
    void test_strength_is_consistent() {
        for (int i = 0; i < 10; i++) {
            strength.calculStrenght();
            assertTrue(strength.getStrength() >= 0 && strength.getStrength() <= 100);
        }
    }
    
    @Test
    void test_get_Algorith() {
    	assertEquals(algo, strength.getAlgorithm());
    }

}