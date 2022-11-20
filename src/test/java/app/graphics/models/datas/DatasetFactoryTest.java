package app.graphics.models.datas;

import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import app.utils.ProjectUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DatasetFactoryTest {
    ReferenceDataset<IrisData> irisDataReferenceDS = DatasetFactory.irisReferenceDataset("iristest");
    ReferenceDataset<TitanicPassengerData> titanicPassengerDataReferenceDS = DatasetFactory.titanicPassengerReferenceDataset("titanictest");

    @Test
    void test_create_reference_dataset_from_type() {
        // @Todo : refactor tout ça
        ReferenceDataset<IrisData> factoryIrisDataDS = DatasetFactory.createReferenceDataset("test", DataType.IRIS);
        ReferenceDataset<TitanicPassengerData> factoryTitanicPassengerDataDS = DatasetFactory.createReferenceDataset("test", DataType.PASSENGER);
        int factoryIrisDataDSSize = factoryIrisDataDS.getDatas().size();
        int factoryTitanicPassengerDataDSSize = factoryTitanicPassengerDataDS.getDatas().size();
        int irisDataReferenceDSSize = irisDataReferenceDS.getDatas().size();
        int titanicPassengerDataDSSize = titanicPassengerDataReferenceDS.getDatas().size();


        assertEquals(irisDataReferenceDSSize, factoryIrisDataDSSize);
        assertEquals(titanicPassengerDataDSSize, factoryTitanicPassengerDataDSSize);
        assertNotEquals(irisDataReferenceDSSize, factoryTitanicPassengerDataDSSize);
        assertNotEquals(titanicPassengerDataDSSize, factoryIrisDataDSSize);
    }


    @Test
    void factory_should_create_working_dataset() {
        WorkingDataset<IrisData> workingDS;
        File csvFile = ProjectUtils.getFile("/data/iris.csv");
        workingDS = DatasetFactory.createWorkingDataset("irisDataSet", DataType.IRIS, csvFile);
        assertNotEquals(null, workingDS);
    }

}