package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;
import app.graphics.models.datas.data.TitanicPassengerData;

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

		try {
			assertEquals(0, this.eucliGeometry.distance(irisWork, irisWork));
			assertEquals(0, this.eucliGeometry.distance(irisRef, irisRef));
			assertEquals(5.0, this.eucliGeometry.distance(irisWork, irisRef));
		} catch (FieldToDistanceException e) {
			assertTrue(false);
		}
	}

	@Test
	void test_distance_with_not_originally_double_fields() {
		this.eucliGeometry.addField("variety");
		this.irisRef.setVariety(IrisVariety.SETOSA);
		this.irisWork.setVariety(IrisVariety.VIRGINICA);
		
		try {
			assertEquals(1.0, this.eucliGeometry.distance(irisWork, irisRef));
			assertEquals(0, this.eucliGeometry.distance(irisWork, irisWork));
			assertEquals(0, this.eucliGeometry.distance(irisRef, irisRef));
		} catch (FieldToDistanceException e) {
			assertTrue(false);
		}
	}
	
	@Test
	void test_distance_with_not_originally_double_fields_exception() {
		TitanicPassengerData passengerRef = new TitanicPassengerData();
		TitanicPassengerData passengerWork = new TitanicPassengerData();
		EuclideanGeometry<TitanicPassengerData> eucliGeometryPassenger = new EuclideanGeometry<TitanicPassengerData>();
		eucliGeometryPassenger.addField("cabin");
		passengerRef.setCabin("no");
		passengerWork.setCabin("yes");
		
		assertThrows(FieldToDistanceException.class, () -> eucliGeometryPassenger.distance(passengerWork, passengerRef));
	}
}
