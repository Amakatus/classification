package app.models.algorithm;

import app.models.algorithm.calculators.DistanceCalculator;
import app.models.algorithm.calculators.RandomDistanceCalculator;
import app.models.datas.DatasetFactory;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAlgorithmTest {
    AbstractAlgorithm<IrisData> algorithm;
    IrisData irisOne = new IrisData();
    IrisData irisTwo = new IrisData();

    public AbstractAlgorithmTest() {
        WorkingDataset<IrisData> workingDataset = new WorkingDataset<>(Arrays.asList(irisOne, irisTwo), DatasetFactory.irisReferenceDataset("Test"));
        workingDataset.addDistanceFieldString("petalLength");
        irisOne.setPetalLength(2);
        irisOne.setPetalLength(15);
        workingDataset.setCategoryField("variety");
        this.algorithm = workingDataset.createKNN(3);
    }

    @Test
    void test_algorithm_strength_calculator_should_not_be_null() {
        assertNotNull(algorithm.getStrengthCalculator());
    }

    @Test
    void test_algorithm_strength_value_should_be_zero() {
        assertEquals(0, algorithm.getStrength());
    }

    @Test
    void test_algorithm_workingdataset_should_exist_and_have_data() {
        assertNotNull(algorithm.getWorkingDataset());
        assertFalse(algorithm.getWorkingDataset().getData().isEmpty());
    }

    @Test
    void test_algorithm_referencedataset_should_exist_and_have_data() {
        assertNotNull(algorithm.getReferenceDataset());
        assertFalse(algorithm.getReferenceDataset().getData().isEmpty());
    }

    @Test
    void test_algorithm_can_classify_data() {
        assertNull(irisOne.getVariety());
        assertNull(irisTwo.getVariety());
        algorithm.classifyWorkingDataset();
        assertNotNull(irisOne.getVariety());
        assertNotNull(irisTwo.getVariety());
    }

    @Test
    void test_algorithm_can_generate_strength_score() {
        assertEquals(0, algorithm.getStrength());
        algorithm.generateStrength();
        assertTrue(algorithm.getStrength() > 0);
    }

    @Test
    void test_algorithm_classifier_should_not_be_null() {
        assertNotNull(algorithm.getClassifier());
    }

    @Test
    void test_algorithm_calculator_should_not_be_null() {
        assertNotNull(algorithm.getCalculator());
    }

    @Test
    void test_algorithm_should_set_new_calculator() {
        this.algorithm.setCalculator("");
        assertFalse(this.algorithm.getCalculator().getClass().isAssignableFrom(RandomDistanceCalculator.class));
        assertTrue(this.algorithm.getCalculator().getClass().isAssignableFrom(DistanceCalculator.class));
        this.algorithm.setCalculator("Random");
        assertTrue(this.algorithm.getCalculator().getClass().isAssignableFrom(RandomDistanceCalculator.class));
        this.algorithm.setCalculator("Distance");
        assertTrue(this.algorithm.getCalculator().getClass().isAssignableFrom(DistanceCalculator.class));
    }
}