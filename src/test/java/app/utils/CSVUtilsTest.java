package app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;

class CSVUtilsTest {

	@Test
	void testLoadIrisCSV() {
		List<IrisData> irisDatas = CSVUtils.loadIrisCSV();
		assertEquals(IrisVariety.SETOSA, irisDatas.get(0).getVariety());
		assertEquals(150, irisDatas.size());
	}

}