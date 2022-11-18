package app.algorithm.normalizers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.graphics.models.datas.DataDeltas;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.utils.ClassUtils;

import static org.junit.jupiter.api.Assertions.*;

class NormalizerTest {
	ReferenceDataset<IrisData> rds = new ReferenceDataset<>("rds");
	IrisData iris1 = new IrisData();
	IrisData iris2 = new IrisData();
	IrisData iris3 = new IrisData();
	int kNeighbours;
	WorkingDataset<IrisData> workingDS;
	DataDeltas deltasPetalLength;

	@BeforeEach
	void init() {
		kNeighbours = 1;
		iris1.setPetalLength(5.0);
		iris2.setPetalLength(6.0);
		iris3.setPetalLength(10.0);
		iris1.setPetalWidth(0.0);
		iris2.setPetalWidth(5.0);
		iris3.setPetalWidth(10.0);
		rds.addData(iris1, iris2, iris3);
		rds.registerDeltas();
		deltasPetalLength = rds.getDeltas().get("petalLength");
	}

	@Test
	void test_values_of_iris_should_be_between_0_and_1_after_normalize() {
		List<Field> fields = new ArrayList<>();
		fields.addAll(ClassUtils.getNumberFields(rds.getDatas().get(0)));
		Map<String, DataDeltas> deltaOfDataset = rds.getDeltas();
		Normalizer normalizer = new Normalizer();
		for (IrisData data : rds.getDatas()) {
			normalizer.normalize(data, deltaOfDataset);
		}
		assertEquals(0.0, rds.getDatas().get(0).getPetalLength());
		assertEquals(0.2, rds.getDatas().get(1).getPetalLength());
		assertEquals(1.0, rds.getDatas().get(2).getPetalLength());
		assertEquals(0.0, rds.getDatas().get(0).getPetalWidth());
		assertEquals(0.5, rds.getDatas().get(1).getPetalWidth());
		assertEquals(1.0, rds.getDatas().get(2).getPetalWidth());
	}
	
	@Test
	void test_values_of_iris_should_be_return_to_initial_values() {
		List<Field> fields = new ArrayList<>();
		fields.addAll(ClassUtils.getNumberFields(rds.getDatas().get(0)));
		Map<String, DataDeltas> deltaOfDataset = rds.getDeltas();
		Normalizer normalizer = new Normalizer();
		for (IrisData data : rds.getDatas()) {
			normalizer.normalize(data, deltaOfDataset);
		}
		for (IrisData data : rds.getDatas()) {
			normalizer.denormalize(data, deltaOfDataset);
		}
		assertEquals(5.0, rds.getDatas().get(0).getPetalLength());
		assertEquals(6.0, rds.getDatas().get(1).getPetalLength());
		assertEquals(10.0, rds.getDatas().get(2).getPetalLength());
		assertEquals(0.0, rds.getDatas().get(0).getPetalWidth());
		assertEquals(5.0, rds.getDatas().get(1).getPetalWidth());
		assertEquals(10.0, rds.getDatas().get(2).getPetalWidth());
	}

}
