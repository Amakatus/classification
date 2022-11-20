package app.algorithm;

import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmFactoryTest {

    @Test
    void testCreateAlgorithm() {
        ReferenceDataset<IrisData> reference = DatasetFactory.irisReferenceDataset("test");
        IrisData toClassify = new IrisData();
        WorkingDataset<IrisData> working = new WorkingDataset<>("WorkingDS", Arrays.asList(toClassify), reference);
        AlgorithmFactory.createKNN(working, 5);
        assertEquals(1, working.getAlgorithms().size());
        assertEquals(5, working.getAlgorithms().get(0).getKNeighbours());
    }

}
