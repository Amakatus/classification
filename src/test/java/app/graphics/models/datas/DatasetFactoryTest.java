package app.graphics.models.datas;

import app.graphics.models.datas.data.DataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatasetFactoryTest {
    @Test
    void test_create_reference_dataset_from_type() {
        // @Todo : refactor tout ça
        assertEquals(DatasetFactory.irisReferenceDataset("test").getDatas().size(), DatasetFactory.createReferenceDataset("test", DataType.IRIS).getDatas().size());
        assertEquals(DatasetFactory.titanicPassengerReferenceDataset("test").getDatas().size(), DatasetFactory.createReferenceDataset("test", DataType.PASSENGER).getDatas().size());
        assertNotEquals(DatasetFactory.irisReferenceDataset("test").getDatas().size(), DatasetFactory.createReferenceDataset("test", DataType.PASSENGER).getDatas().size());
        assertNotEquals(DatasetFactory.titanicPassengerReferenceDataset("test").getDatas().size(), DatasetFactory.createReferenceDataset("test", DataType.IRIS).getDatas().size());
    }
}