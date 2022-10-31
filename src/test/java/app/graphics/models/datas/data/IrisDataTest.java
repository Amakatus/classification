package app.graphics.models.datas.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IrisDataTest {

	@Test
	void should_get_sepal_length() {
		IrisData iris = new IrisData();
		iris.setPetalLength(22.7);
		assertEquals(22.7,iris.getPetalLength());
	}

}
