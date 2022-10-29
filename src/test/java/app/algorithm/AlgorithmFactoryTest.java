package app.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import app.graphics.models.DatasetType;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.data.IrisData;
import app.utils.CSVUtils;

class AlgorithmFactoryTest {

	@Test
	void testCreateAlgorithm() {
		try {
			Dataset<IrisData> reference = new Dataset<IrisData>("ReferenceDS", CSVUtils.loadIrisCSV());
			IrisData toClassify = new IrisData();
			Dataset<IrisData> working = new Dataset<IrisData>("WorkingDS", Arrays.asList(toClassify), DatasetType.WORKING);
			assertTrue(AlgorithmFactory.createAlgorithm(working, reference, 5));
			assertEquals(1, working.getAlgorithms().size());
			assertEquals(5, working.getAlgorithms().get(0).getK());
			assertFalse(AlgorithmFactory.createAlgorithm(working, working, 5));
			assertFalse(AlgorithmFactory.createAlgorithm(reference, reference, 5));
			assertFalse(AlgorithmFactory.createAlgorithm(reference, working, 5));
		} catch (IOException e) { e.printStackTrace(); }
	}

}
