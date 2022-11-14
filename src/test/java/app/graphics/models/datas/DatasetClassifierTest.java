package app.graphics.models.datas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;

class DatasetClassifierTest {
	protected ReferenceDataset<IrisData> rDS;
	protected WorkingDataset<IrisData> wDS;
	protected IrisData irisOne;
	protected IrisData irisTwo;
	protected KNNAlgorithm<IrisData> algo;
	protected DatasetClassifier<IrisData> classifier;
	
	public DatasetClassifierTest() {
		rDS = DatasetFactory.irisReferenceDataset("rds");
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
	void test_classify_datas() {
		assertNull(irisOne.getVariety());
		assertNull(irisTwo.getVariety());
		classifier.classifyDatas();
		assertEquals(IrisVariety.VERSICOLOR, irisOne.getVariety());
		assertEquals(IrisVariety.VIRGINICA, irisTwo.getVariety());
		this.wDS.setCategoryField("petalWidth");
		classifier.classifyDatas();
		assertEquals(IrisVariety.VERSICOLOR, irisOne.getVariety());
		assertEquals(IrisVariety.VIRGINICA, irisTwo.getVariety());
	}

}
