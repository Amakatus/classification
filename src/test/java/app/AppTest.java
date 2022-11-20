package app;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    App app = null;
    ReferenceDataset<IrisData> irisDataReferenceDS = new ReferenceDataset<>("irisDataReference");
    ReferenceDataset<TitanicPassengerData> titanicPassengerDataReferenceDS = new ReferenceDataset<>("titanicPassengerDataReference");

    @BeforeEach
    void initApp() {
        this.app = App.getInstance();
        app.clearReferenceDatasets();
        app.clearWorkingDatasets();
    }

    @Test
    void testGetInstance() {
        assertSame(app, App.getInstance());
    }

    @Test
    void testAddWorkingDataset() {
        assertTrue(app.getWorkingDatasets().isEmpty());
        app.addWorkingDataset(new WorkingDataset<IrisData>("irisDataReference", irisDataReferenceDS));
        assertEquals(1, app.getWorkingDatasets().size());
    }

    @Test
    void should_add_multiple_working_data_sets() {
        assertTrue(app.getWorkingDatasets().isEmpty());
        app.addWorkingDataset(new WorkingDataset<IrisData>("irisDataReference", irisDataReferenceDS), new WorkingDataset<TitanicPassengerData>("TitanicDataset", titanicPassengerDataReferenceDS));
        assertEquals(2, app.getWorkingDatasets().size());
    }

    @Test
    void testGetWorkingDatasets() {
        app.addWorkingDataset(new WorkingDataset<IrisData>("TestPurposeWorkingDataset", irisDataReferenceDS));
        assertEquals("TestPurposeWorkingDataset", app.getWorkingDatasets().get(0).getTitle());
    }

    @Test
    void testAddReferenceDataset() {
        assertTrue(app.getReferenceDatasets().isEmpty());
        app.addReferenceDataset(new ReferenceDataset<IrisData>("IrisDataReference"));
        assertEquals(1, app.getReferenceDatasets().size());
    }

    @Test
    void testGetReferenceDatasets() {
        app.addReferenceDataset(new ReferenceDataset<IrisData>("TestPurposeReferenceDataset"));
        assertEquals("TestPurposeReferenceDataset", app.getReferenceDatasets().get(0).getTitle());
    }

    @Test
    void testLoadReferenceDatasets() {
        assertTrue(app.getReferenceDatasets().isEmpty());
        app.loadReferenceDatasets();
        assertFalse(app.getReferenceDatasets().isEmpty());
    }
}
