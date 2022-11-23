package app.models.algorithm.classifiers;

import app.models.algorithm.AlgorithmFactory;
import app.models.algorithm.KNNAlgorithm;
import app.models.datas.DatasetFactory;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KNNDistanceClassifierTest {
    protected ReferenceDataset<IrisData> rDS;
    protected WorkingDataset<IrisData> wDS;
    protected IrisData irisOne;
    protected IrisData irisTwo;
    protected KNNAlgorithm<IrisData> algo;

    public KNNDistanceClassifierTest() {
        rDS = DatasetFactory.irisReferenceDataset("rds");
        wDS = new WorkingDataset<>(rDS);
        wDS.setCategoryField("variety");
        wDS.addDistanceFieldString("petalLength");
        wDS.addDistanceFieldString("petalWidth");
        irisOne = new IrisData();
        irisTwo = new IrisData();
        irisOne.setPetalLength(5);
        irisOne.setPetalWidth(1);
        irisTwo.setPetalLength(30);
        irisTwo.setPetalWidth(1.3);
        wDS.addData(irisOne);
        wDS.addData(irisTwo);
        algo = wDS.createKNN(3);
    }

    @Test
    void test_should_classify_datas_for_given_category() {
        assertNull(irisOne.getVariety());
        assertNull(irisTwo.getVariety());
        algo.classifyWorkingDataset();
        assertNotNull(irisOne.getVariety());
        assertNotNull(irisTwo.getVariety());
    }
}