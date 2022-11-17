package app.algorithm.normalizers;


	import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
	import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Refreshable;

import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import app.algorithm.AlgorithmFactory;
	import app.algorithm.KNNAlgorithm;
	import app.algorithm.KNNCalculator;
	import app.graphics.models.datas.DataDeltas;
	import app.graphics.models.datas.ReferenceDataset;
	import app.graphics.models.datas.WorkingDataset;
	import app.graphics.models.datas.data.Data;
	import app.graphics.models.datas.data.IrisData;
import app.utils.ClassUtils;

import static org.junit.jupiter.api.Assertions.*;

class NormalizerTest {	
		IrisData wIrisOne;
		IrisData wIrisTwo;
		IrisData rIrisOne;
		IrisData rIrisTwo;
		int kNeighbours;
		ReferenceDataset<IrisData> referenceDS;
		WorkingDataset<IrisData> workingDS;
		KNNAlgorithm<IrisData> knnAlgorithm;
		KNNCalculator<IrisData> calculator;

		@BeforeEach
		void init() {
			wIrisOne = new IrisData();
			wIrisTwo = new IrisData();
			rIrisOne = new IrisData();
			rIrisTwo = new IrisData();
			kNeighbours = 1;
			referenceDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo, wIrisOne));
			workingDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo), referenceDS);
			AlgorithmFactory.createAlgorithm(workingDS, kNeighbours);
			knnAlgorithm = workingDS.getAlgorithms().get(0);
			calculator = knnAlgorithm.getCalculator();
			wIrisOne.setPetalLength(5.0);
			rIrisOne.setPetalLength(6.0);
			wIrisTwo.setPetalLength(10.0);
			rIrisTwo.setPetalLength(9.0);
			wIrisOne.setPetalWidth(10.0);
			rIrisOne.setPetalWidth(25.0);
			wIrisTwo.setPetalWidth(25.0);
			rIrisTwo.setPetalWidth(10.0);
		}
		
		@Test
		void Normalizer_test() throws IllegalArgumentException, IllegalAccessException {
			List<Field> fields = new ArrayList<>();
			fields.addAll(ClassUtils.getNumberFields(referenceDS.getDatas().get(0)));
			
			Map<String, DataDeltas> test = new HashMap<>();
			test = referenceDS.getDeltas();
			Normalizer normalizer = new Normalizer();
			for(int cpt = 0 ; cpt < referenceDS.getDatas().size(); cpt++) {
				fields.addAll(normalizer.normalize(referenceDS.getDatas().get(cpt),test));
			}
			
				for (Field field : fields) {
					System.out.println("test");
					System.out.println(field.getDouble(referenceDS.getDatas().get(0)));
					assertEquals(0,field.getDouble(referenceDS.getDatas()));
				}
			
		}

		

	
	

}
