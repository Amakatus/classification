package app.algorithm.geometry;

import app.algorithm.KNNAlgorithm;
import app.algorithm.KNNCalculator;
import app.exceptions.FieldToDistanceException;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.IrisVariety;
import app.graphics.models.datas.data.TitanicPassengerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanGeometryTest {
    IrisData irisRef;
    IrisData irisWork;
    WorkingDataset<IrisData> wDS;
    ReferenceDataset<IrisData> rDS;
    KNNAlgorithm<IrisData> algo;
    KNNCalculator<IrisData> calculator;
    ManhattanGeometry<IrisData> geometry;

    @BeforeEach
    void init() {
        this.irisRef = new IrisData();
        this.irisWork = new IrisData();
        this.rDS = new ReferenceDataset<>("rds");
        this.wDS = new WorkingDataset<>("wds", rDS);
        this.algo = new KNNAlgorithm<>(wDS, 5);
        this.calculator = algo.getCalculator();
        this.algo.getDatasKNN(GeometryFactory.createManhattanGeometry(calculator));
        this.geometry = (ManhattanGeometry<IrisData>) this.calculator.getGeometry();
    }

    @Test
    void test_distance_with_originally_double_fields() {
        this.irisRef.setPetalLength(20);
        this.irisWork.setPetalLength(10);

        this.wDS.addDistanceFieldString("petalLength");

        try {
            assertEquals(0, this.geometry.distance(irisWork, irisWork));
            assertEquals(0, this.geometry.distance(irisRef, irisRef));
            assertEquals(10.0, this.geometry.distance(irisWork, irisRef));
        } catch (FieldToDistanceException e) {
            fail();
        }
    }

    @Test
    void test_distance_with_not_originally_double_fields() {
        this.wDS.addDistanceFieldString("variety");
        this.irisRef.setVariety(IrisVariety.VERSICOLOR);
        this.irisWork.setVariety(IrisVariety.VIRGINICA);

        try {
            assertEquals(1.0, this.geometry.distance(irisWork, irisRef));
            assertEquals(1.0, this.geometry.distance(irisRef, irisWork));
            assertEquals(0, this.geometry.distance(irisWork, irisWork));
            assertEquals(0, this.geometry.distance(irisRef, irisRef));
        } catch (FieldToDistanceException e) {
            fail();
        }
    }

    @Test
    void test_distance_with_nor_double_or_method_fields() {
        ReferenceDataset<TitanicPassengerData> referenceDataset = new ReferenceDataset<>("TitanicPassengerData");
        WorkingDataset<TitanicPassengerData> workingDataset = new WorkingDataset<>("TitanicPassengerDataWorking", referenceDataset);
        KNNAlgorithm<TitanicPassengerData> algo = new KNNAlgorithm<>(workingDataset, 3);
        algo.getDatasKNN();
        KNNCalculator<TitanicPassengerData> calc = algo.getCalculator();
        TitanicPassengerData rPass = new TitanicPassengerData();
        TitanicPassengerData wPass = new TitanicPassengerData();
        rPass.setName("Pierre");
        wPass.setName("Paul");
        EuclideanGeometry<TitanicPassengerData> geo = (EuclideanGeometry<TitanicPassengerData>) calc.getGeometry();
        try {
            workingDataset.addDistanceFieldString("name");
            assertEquals(1, geo.distance(wPass, rPass));
            workingDataset.addDistanceFieldString("cabin");
            assertEquals(Double.MAX_VALUE, geo.findDistanceForField(wPass, rPass, "cabin"));
            assertThrows(FieldToDistanceException.class, () -> geo.distance(wPass, rPass));
        } catch (FieldToDistanceException e) {
            fail();
        }
    }
}
