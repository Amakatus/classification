package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.algorithm.KNNAlgorithm;
import app.algorithm.KNNCalculator;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

class GeometryCalculatorTest {
	WorkingDataset<IrisData> wDS;
	ReferenceDataset<IrisData> rDS;
	KNNAlgorithm<IrisData> algo;
	KNNCalculator<IrisData> calculator;
	
	@BeforeEach
	void init() {
		rDS = new ReferenceDataset<>("rds");
		wDS = new WorkingDataset<>("wds", rDS);
		algo = new KNNAlgorithm<>(wDS, 5);
		calculator = algo.getCalculator();
	}
	
	@Test
	void should_add_field_in_list() {
		assertTrue(this.calculator.getFieldsNames().isEmpty());
		this.wDS.addDistanceFieldString("iris1");
		assertEquals("iris1",this.calculator.getFieldsNames().get(0));
		assertEquals(1,this.calculator.getFieldsNames().size());
	}
	
	@Test
	void should_remove_field_in_list() {
		assertEquals(0, this.calculator.getFieldsNames().size());
		this.wDS.addDistanceFieldString("iris1");
		assertEquals(1, this.calculator.getFieldsNames().size());
		this.wDS.removeDistanceFieldString("iris1");
		assertEquals(0, this.calculator.getFieldsNames().size());
		this.wDS.removeDistanceFieldString("iris2");
		assertEquals(0, this.calculator.getFieldsNames().size());
	}

}
