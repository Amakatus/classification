package app.models.datas.data.normalizers;

import app.models.datas.DatasetFactory;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IDataNormalizerTest {
    WorkingDataset<IrisData> workingDataset;
    ReferenceDataset<IrisData> referenceDataset;
    IrisData irisOne = new IrisData();

    public IDataNormalizerTest() {
        irisOne.setPetalLength(3);
        irisOne.setPetalWidth(1.2);
        workingDataset = new WorkingDataset<>(List.of(irisOne), DatasetFactory.irisReferenceDataset("Test"));
        referenceDataset = workingDataset.getReferenceDataset();
        workingDataset.unNormalizeDatas();
    }

    @Test
    void test_normalizer_should_normalize_data() {
        assertEquals(3, irisOne.getPetalLength());
        assertEquals(1.2, irisOne.getPetalWidth());
        IDataNormalizer.normalize(irisOne, referenceDataset.getDeltas());
        assertTrue(irisOne.getPetalLength() >= 0 && irisOne.getPetalLength() <= 1);
        assertTrue(irisOne.getPetalWidth() >= 0 && irisOne.getPetalLength() <= 1);
    }

    @Test
    void test_normalizer_should_denormalize_data() {
        assertEquals(3, irisOne.getPetalLength());
        assertEquals(1.2, irisOne.getPetalWidth());
        IDataNormalizer.normalize(irisOne, referenceDataset.getDeltas());
        assertTrue(irisOne.getPetalLength() >= 0 && irisOne.getPetalLength() <= 1);
        assertTrue(irisOne.getPetalWidth() >= 0 && irisOne.getPetalLength() <= 1);
        IDataNormalizer.denormalize(irisOne, referenceDataset.getDeltas());
        assertEquals(3, irisOne.getPetalLength());
        assertEquals(1.2, irisOne.getPetalWidth());
    }
}