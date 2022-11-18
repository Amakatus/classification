package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;

class AppTest {
	App app = null;
	ReferenceDataset<IrisData> referenceDS = new ReferenceDataset<>("refDS");
	ReferenceDataset<TitanicPassengerData> refenceDS2 = new ReferenceDataset<>("refDS2");
	
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
		app.addWorkingDataset(new WorkingDataset<IrisData>("RandomDataset", referenceDS));
		assertEquals(1, app.getWorkingDatasets().size());
	}
	
	@Test
	void should_add_multiple_working_data_sets() {
		assertTrue(app.getWorkingDatasets().isEmpty());
		app.addWorkingDataset(new WorkingDataset<IrisData>("RandomDataset",referenceDS),new WorkingDataset<TitanicPassengerData>("TitanicDataset",refenceDS2));
		assertEquals(2, app.getWorkingDatasets().size());
	}

	@Test
	void testGetWorkingDatasets() {
		app.addWorkingDataset(new WorkingDataset<IrisData>("TestPurposeWorkingDataset", referenceDS));
		assertEquals("TestPurposeWorkingDataset",app.getWorkingDatasets().get(0).getTitle());
	}

	@Test
	void testAddReferenceDataset() {
		assertTrue(app.getReferenceDatasets().isEmpty());
		app.addReferenceDataset(new ReferenceDataset<IrisData>("RandomDataset"));
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
