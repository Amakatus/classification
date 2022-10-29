package app.graphics.models.datas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.IrisData;

class DatasetTest {
	Dataset<IrisData> testDataset = null;
	
	@BeforeEach
	void initDataset() {
		this.testDataset = new Dataset<>("testDataset");
	}
	
	@Test
	void testGetTitle() {
		assertEquals("testDataset", this.testDataset.getTitle());
	}
	
	@Test
	void testAddData() {
		assertTrue(this.testDataset.getDatas().isEmpty());
		this.testDataset.addData(new IrisData());
		assertEquals(1, this.testDataset.getDatas().size());
	}

	@Test
	void testGetAlgorithms() {
		assertTrue(this.testDataset.getAlgorithms().isEmpty());
	}

	@Test
	void testAddKNNAlgorithm() {
		assertTrue(this.testDataset.getAlgorithms().isEmpty());
		new KNNAlgorithm<IrisData>(this.testDataset, 5);
		assertEquals(1, this.testDataset.getAlgorithms().size());
	}


	@Test
	void testToString() {
		assertEquals("testDataset", this.testDataset.toString());
	}

}
