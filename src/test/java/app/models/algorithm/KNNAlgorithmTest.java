package app.models.algorithm;

import app.models.datas.DatasetFactory;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNAlgorithmTest {
    KNNAlgorithm<IrisData> knnAlgorithm;
    IrisData irisOne = new IrisData();
    IrisData irisTwo = new IrisData();

    public KNNAlgorithmTest() {
        WorkingDataset<IrisData> workingDataset = new WorkingDataset<>(Arrays.asList(irisOne, irisTwo), DatasetFactory.irisReferenceDataset("Test"));
        workingDataset.addDistanceFieldString("petalLength");
        irisOne.setPetalLength(2);
        irisOne.setPetalLength(15);
        workingDataset.setCategoryField("variety");
        this.knnAlgorithm = workingDataset.createKNN(3, true);
    }

    @Test
    void test_algorithm_auto_classified_itself() {
        assertNotNull(irisOne.getVariety());
        assertNotNull(irisTwo.getVariety());
    }

    @Test
    void test_to_string_should_have_k_geo_and_strength() {
        assertEquals("3NNAlgorithm E (" + knnAlgorithm.getStrength() + "%)", knnAlgorithm.toString());
    }
}