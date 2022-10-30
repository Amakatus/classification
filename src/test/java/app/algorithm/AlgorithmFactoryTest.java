package app.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.utils.CSVUtils;

class AlgorithmFactoryTest {

	@Test
	void testCreateAlgorithm() {
		ReferenceDataset<IrisData> reference = new ReferenceDataset<IrisData>("ReferenceDS", CSVUtils.loadIrisCSV());
		IrisData toClassify = new IrisData();
		WorkingDataset<IrisData> working = new WorkingDataset<IrisData>("WorkingDS", Arrays.asList(toClassify));
		AlgorithmFactory.createAlgorithm(working, reference, 5);
		assertEquals(1, working.getAlgorithms().size());
		assertEquals(5, working.getAlgorithms().get(0).getK());
	}

}
