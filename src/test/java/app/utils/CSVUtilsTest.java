package app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;

class CSVUtilsTest {

	@Test
	void testLoadIrisCSV() {
		List<IrisData> irisDatas = CSVUtils.loadCSV("/data/iris.csv", DataType.IRIS);
		assertEquals(IrisVariety.SETOSA, irisDatas.get(0).getVariety());
		assertEquals(150, irisDatas.size());
	}

}