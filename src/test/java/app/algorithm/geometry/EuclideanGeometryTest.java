package app.algorithm.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.algorithm.KNNAlgorithm;
import app.algorithm.KNNCalculator;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;

class EuclideanGeometryTest {
	IrisData irisRef;
	IrisData irisWork;
	WorkingDataset<IrisData> wDS;
	ReferenceDataset<IrisData> rDS;
	KNNAlgorithm<IrisData> algo;
	KNNCalculator<IrisData> calculator;
	EuclideanGeometry<IrisData> geometry;

	@BeforeEach
	void init() {
		this.irisRef = new IrisData();
		this.irisWork = new IrisData();
		this.rDS = new ReferenceDataset<>("rds");
		this.wDS = new WorkingDataset<>("wds", rDS);
		this.algo = new KNNAlgorithm<>(wDS, 5);
		this.algo.getDatasKNN();
		this.calculator = algo.getCalculator();
		this.geometry = (EuclideanGeometry<IrisData>) this.calculator.getGeometry();
	}

	@Test
	void test_distance_with_originally_double_fields() {
		this.irisRef.setPetalLength(5);
		this.irisWork.setPetalLength(10);

		this.wDS.addDistanceFieldString("petalLength");

		try {
			assertEquals(0, this.geometry.distance(irisWork, irisWork));
			assertEquals(0, this.geometry.distance(irisRef, irisRef));
			assertEquals(5.0, this.geometry.distance(irisWork, irisRef));
		} catch (FieldToDistanceException e) {
			assertTrue(false);
		}
	}

	@Test
	void test_distance_with_not_originally_double_fields() {
		this.wDS.addDistanceFieldString("variety");
		this.irisRef.setVariety(IrisVariety.SETOSA);
		this.irisWork.setVariety(IrisVariety.VIRGINICA);
		
		try {
			assertEquals(1.0, this.geometry.distance(irisWork, irisRef));
			assertEquals(0, this.geometry.distance(irisWork, irisWork));
			assertEquals(0, this.geometry.distance(irisRef, irisRef));
		} catch (FieldToDistanceException e) {
			assertTrue(false);
		}
	}
	
	/*@Test
	void test_distance_with_not_originally_double_fields_exception() {
		TitanicPassengerData passengerRef = new TitanicPassengerData();
		TitanicPassengerData passengerWork = new TitanicPassengerData();
		EuclideanGeometry<TitanicPassengerData> eucliGeometryPassenger = new EuclideanGeometry<TitanicPassengerData>();
		eucliGeometryPassenger.addField("cabin");
		passengerRef.setCabin("no");
		passengerWork.setCabin("yes");
		
		assertThrows(FieldToDistanceException.class, () -> eucliGeometryPassenger.distance(passengerWork, passengerRef));
	}*/
}
