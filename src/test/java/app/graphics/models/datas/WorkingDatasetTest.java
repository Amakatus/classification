package app.graphics.models.datas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.IrisData;

class WorkingDatasetTest {
	
	
	@Test
	void Test_setter_and_getter_categoriField() {
		ReferenceDataset<IrisData> referenceDS = new ReferenceDataset<IrisData>("rDS");
		WorkingDataset<IrisData> workingDS = new WorkingDataset<IrisData>("wDS", referenceDS);
		workingDS.setCategoryField("petalLenght");
		assertEquals("petalLenght", workingDS.getCategoryField());
	}
}
