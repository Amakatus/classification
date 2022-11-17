package app.graphics.models.datas;

import app.graphics.models.datas.data.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceDatasetFactoryTest {
    @Test
    void test_create_reference_dataset_from_type() {
        // @Todo : refactor tout ça
        assertEquals(ReferenceDatasetFactory.irisReferenceDataset("test").getDatas().size(), ReferenceDatasetFactory.createReferenceDataset("test", DataType.IRIS).getDatas().size());
        assertEquals(ReferenceDatasetFactory.titanicPassengerReferenceDataset("test").getDatas().size(), ReferenceDatasetFactory.createReferenceDataset("test", DataType.PASSENGER).getDatas().size());
        assertNotEquals(ReferenceDatasetFactory.irisReferenceDataset("test").getDatas().size(), ReferenceDatasetFactory.createReferenceDataset("test", DataType.PASSENGER).getDatas().size());
        assertNotEquals(ReferenceDatasetFactory.titanicPassengerReferenceDataset("test").getDatas().size(), ReferenceDatasetFactory.createReferenceDataset("test", DataType.IRIS).getDatas().size());
    }
}