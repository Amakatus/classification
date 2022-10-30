package app.algorithm.geometry;

import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.IrisData;

class EuclideanGeometryTest {

	@Test
	void testDistance() {
		IrisData irisOne = new IrisData();
		IrisData irisTwo = new IrisData();
		
		irisOne.setPetalLength(5);
		irisTwo.setPetalLength(10);
		
		EuclideanGeometry<IrisData> gEucli = new EuclideanGeometry<>();
	}

}
