package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.graphics.models.datas.data.IrisData;

class EuclideanGeometryTest {
	IrisData irisRef;
	IrisData irisWork;
	EuclideanGeometry<IrisData> eucliGeometry;
	
	@BeforeEach
	void init() {
		this.irisRef = new IrisData();
		this.irisWork = new IrisData();
		this.eucliGeometry = new EuclideanGeometry<IrisData>();
	}
	
	@Test
	void test_distance_with_originally_double_fields() {
		this.irisRef.setPetalLength(5);
		this.irisWork.setPetalLength(10);
		
		this.eucliGeometry.addField("petalLength");
		
		assertEquals(0, this.eucliGeometry.distance(irisWork, irisWork));
		assertEquals(0, this.eucliGeometry.distance(irisRef, irisRef));
		assertEquals(5.0, this.eucliGeometry.distance(irisWork, irisRef));
	}
}
