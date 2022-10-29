package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.datas.data.IrisData;
import app.graphics.models.Dataset;

class AppTest {
	App app = null;
	
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
		app.addWorkingDataset(new Dataset<IrisData>("RandomDataset"));
		assertEquals(1, app.getWorkingDatasets().size());
	}

	@Test
	void testGetWorkingDatasets() {
		app.addWorkingDataset(new Dataset<IrisData>("TestPurposeWorkingDataset"));
		assertEquals("TestPurposeWorkingDataset",app.getWorkingDatasets().get(0).getTitle());
	}

	@Test
	void testAddReferenceDataset() {
		assertTrue(app.getReferenceDatasets().isEmpty());
		app.addWorkingDataset(new Dataset<IrisData>("RandomDataset"));
		assertEquals(1, app.getWorkingDatasets().size());
	}
	
	@Test
	void testGetReferenceDatasets() {
		app.addReferenceDataset(new Dataset<IrisData>("TestPurposeReferenceDataset"));
		assertEquals("TestPurposeReferenceDataset", app.getReferenceDatasets().get(0).getTitle());
	}


}
