package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

class AppTest {
	App app = null;
	ReferenceDataset<IrisData> referenceDS = new ReferenceDataset<>("refDS");
	
	@BeforeEach
	void initApp() {
		this.app = App.getInstance();
		app.clearReferenceDatasets();
		app.clearWorkingDatasets();
	}
	
	@Test
	void testGetInstance() {
		assertTrue(app == App.getInstance());
	}
	
	@Test
	void testAddWorkingDataset() {
		assertTrue(app.getWorkingDatasets().isEmpty());
		app.addWorkingDataset(new WorkingDataset<IrisData>("RandomDataset", referenceDS));
		assertEquals(1, app.getWorkingDatasets().size());
	}

	@Test
	void testGetWorkingDatasets() {
		app.addWorkingDataset(new WorkingDataset<IrisData>("TestPurposeWorkingDataset", referenceDS));
		assertEquals("TestPurposeWorkingDataset",app.getWorkingDatasets().get(0).getTitle());
	}

	@Test
	void testAddReferenceDataset() {
		assertTrue(app.getReferenceDatasets().isEmpty());
		app.addWorkingDataset(new ReferenceDataset<IrisData>("RandomDataset"));
		assertEquals(1, app.getWorkingDatasets().size());
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
