package app.models.datas;

import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.models.datas.data.TitanicPassengerData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DatasetFactoryTest {
    ReferenceDataset<IrisData> irisDataReferenceDS = DatasetFactory.irisReferenceDataset("iristest");
    ReferenceDataset<TitanicPassengerData> titanicPassengerDataReferenceDS = DatasetFactory.titanicPassengerReferenceDataset("titanictest");

    @Test
    void test_create_reference_dataset_from_type() {
        // @Todo : refactor tout ça
        ReferenceDataset<IrisData> factoryIrisDataDS = DatasetFactory.createReferenceDataset("test", DataType.IRIS);
        ReferenceDataset<TitanicPassengerData> factoryTitanicPassengerDataDS = DatasetFactory.createReferenceDataset("test", DataType.PASSENGER);
        int factoryIrisDataDSSize = factoryIrisDataDS.getData().size();
        int factoryTitanicPassengerDataDSSize = factoryTitanicPassengerDataDS.getData().size();
        int irisDataReferenceDSSize = irisDataReferenceDS.getData().size();
        int titanicPassengerDataDSSize = titanicPassengerDataReferenceDS.getData().size();

        assertEquals(irisDataReferenceDSSize, factoryIrisDataDSSize);
        assertEquals(titanicPassengerDataDSSize, factoryTitanicPassengerDataDSSize);
        assertNotEquals(irisDataReferenceDSSize, factoryTitanicPassengerDataDSSize);
        assertNotEquals(titanicPassengerDataDSSize, factoryIrisDataDSSize);
    }


    @Test
    void factory_should_create_working_dataset_with_title() {
        WorkingDataset<IrisData> workingDS;
        File csvFile = ProjectUtils.getFile("/data/iris.csv");
        workingDS = DatasetFactory.createWorkingDataset("irisDataSet", DataType.IRIS, csvFile);
        assertNotNull(workingDS);
    }

    @Test
    void factory_should_create_working_dataset_without_title() {
        WorkingDataset<IrisData> workingDS;
        File csvFile = ProjectUtils.getFile("/data/iris.csv");
        workingDS = DatasetFactory.createWorkingDataset(DataType.IRIS, csvFile);
        assertNotNull(workingDS);
        assertEquals("DefaultTitle", workingDS.getTitle());
    }
}