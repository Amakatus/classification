package app.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.DatasetFactory;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;

class AlgorithmFactoryTest {

	@Test
	void testCreateAlgorithm() {
		ReferenceDataset<IrisData> reference = DatasetFactory.irisReferenceDataset("test");
		IrisData toClassify = new IrisData();
		WorkingDataset<IrisData> working = new WorkingDataset<IrisData>("WorkingDS", Arrays.asList(toClassify), reference);
		AlgorithmFactory.createAlgorithm(working, 5);
		assertEquals(1, working.getAlgorithms().size());
		assertEquals(5, working.getAlgorithms().get(0).getKNeighbours());
	}

}
