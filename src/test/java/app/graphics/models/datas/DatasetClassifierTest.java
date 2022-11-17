package app.graphics.models.datas;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DatasetClassifierTest {
	protected ReferenceDataset<IrisData> rDS;
	protected WorkingDataset<IrisData> wDS;
	protected IrisData irisOne;
	protected IrisData irisTwo;
	protected KNNAlgorithm<IrisData> algo;
	protected DatasetClassifier<IrisData> classifier;
	
	public DatasetClassifierTest() {
		rDS = ReferenceDatasetFactory.irisReferenceDataset("rds");
		wDS = new WorkingDataset<>("wds", rDS);
		wDS.setCategoryField("variety");
		wDS.addDistanceFieldString("petalLength");
		wDS.addDistanceFieldString("petalWidth");
		irisOne = new IrisData();
		irisTwo = new IrisData();
		irisOne.setPetalLength(5);
		irisOne.setPetalWidth(1);
		irisTwo.setPetalLength(30);
		irisTwo.setPetalWidth(1.3);
		wDS.addData(irisOne, irisTwo);
		AlgorithmFactory.createKNN(wDS, 3);
		algo = wDS.getAlgorithms().get(0);
		classifier = new DatasetClassifier<>(algo);
	}

	@Test
	void test_should_classify_datas_for_given_category() {
		assertNull(irisOne.getVariety());
		assertNull(irisTwo.getVariety());
		classifier.classifyDatas();
		assertEquals(IrisVariety.VERSICOLOR, irisOne.getVariety());
		assertEquals(IrisVariety.VIRGINICA, irisTwo.getVariety());
		this.wDS.setCategoryField("petalWidth");
		classifier.classifyDatas();
		assertEquals(1.2, irisOne.getPetalWidth());
		assertEquals(2.0, irisTwo.getPetalWidth());
		assertEquals(IrisVariety.VERSICOLOR, irisOne.getVariety());
		assertEquals(IrisVariety.VIRGINICA, irisTwo.getVariety());
	}

}
