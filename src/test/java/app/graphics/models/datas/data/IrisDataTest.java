package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IrisDataTest {

	@Test
	void should_get_sepal_length() {
		IrisData iris = new IrisData();
		iris.setSepalLength(22.7);
		assertEquals(22.7,iris.getPetalLength());
	}
	
	@Test
	void should_get_sepal_width() {
		IrisData iris = new IrisData();
		iris.setSepalWidth(3.09);
		assertEquals(3.09,iris.getPetalWidth());
	}
	
	@Test
	void should_get_petal_length() {
		IrisData iris = new IrisData();
		iris.setPetalLength(3);
		assertEquals(3,iris.getPetalLength());
	}

	@Test
	void should_get_petal_width() {
		IrisData iris = new IrisData();
		iris.setPetalWidth(4);
		assertEquals(4,iris.getPetalWidth());
	}

}
