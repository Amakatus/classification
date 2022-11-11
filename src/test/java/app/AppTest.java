package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

import static org.junit.jupiter.api.Assertions.*;

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
		assertSame(app, App.getInstance());
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
