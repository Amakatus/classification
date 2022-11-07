package app.graphics.models.datas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import app.graphics.models.datas.data.IrisData;

class WorkingDatasetTest {
	
	
	@Test
	void Test_setter_and_getter_categoriField() {
		IrisData wIrisOne = new IrisData();
		IrisData wIrisTwo = new IrisData();
		IrisData rIrisOne = new IrisData();
		IrisData rIrisTwo = new IrisData();
		ReferenceDataset<IrisData> referenceDS = new ReferenceDataset<IrisData>("rDS", Arrays.asList(rIrisOne, rIrisTwo));
		WorkingDataset<IrisData> workingDS = new WorkingDataset<IrisData>("wDS", Arrays.asList(wIrisOne, wIrisTwo), referenceDS);
		workingDS.setCategoryField("PetalLenght");
		assertEquals("PetalLenght", workingDS.getCategoryField());
	}
}
