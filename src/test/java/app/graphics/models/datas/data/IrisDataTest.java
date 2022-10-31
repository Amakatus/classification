package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IrisDataTest {

	@Test
	void should_get_sepal_length() {
		IrisData iris = new IrisData();
		iris.setSepalLength(22.04);
		assertEquals(22.04,iris.getSepalLength());
	}
	
	@Test
	void should_get_sepal_width() {
		IrisData iris = new IrisData();
		iris.setSepalWidth(3.04);
		assertEquals(3.04,iris.getSepalWidth());
	}
	
	@Test
	void should_get_petal_length() {
		IrisData iris = new IrisData();
		iris.setPetalLength(3.03);
		assertEquals(3.03,iris.getPetalLength());
	}

	@Test
	void should_get_petal_width() {
		IrisData iris = new IrisData();
		iris.setPetalWidth(4.21);
		assertEquals(4.21,iris.getPetalWidth());
	}

}
