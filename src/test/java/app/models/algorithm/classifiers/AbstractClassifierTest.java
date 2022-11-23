package app.models.algorithm.classifiers;

import app.models.datas.DatasetFactory;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisVariety;
import app.models.datas.data.TestData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractClassifierTest {
    AbstractClassifier<TestData> classifier;
    TestData testData;

    public AbstractClassifierTest() {
        testData = new TestData();
        WorkingDataset<TestData> workingDataset = new WorkingDataset<TestData>(List.of(testData), new ReferenceDataset<TestData>("test"));
        classifier = new AbstractClassifier<TestData>(workingDataset) {
            @Override
            public void classifyData(TestData data) {

            }
        };
    }

    @Test
    void test_value_are_default_for_test_data() {
        assertNull(testData.string);
        assertFalse(testData.aBoolean);
        assertEquals('a', testData.aChar);
        assertEquals(0, testData.aDouble);
        assertNull(testData.irisVariety);
    }

    @Test
    void test_classifier_should_classify_for_string() {
        classifier.setCategoryField("string");
        classifier.setCategoryForData(testData, "test");
        assertEquals("test", testData.string);
    }

    @Test
    void test_classifier_should_classify_for_boolean() {
        classifier.setCategoryField("aBoolean");
        classifier.setCategoryForData(testData, "true");
        assertTrue(testData.aBoolean);
    }

    @Test
    void test_classifier_should_classify_for_iris_variety() {

        classifier.setCategoryField("irisVariety");
        classifier.setCategoryForData(testData, "SETOSA");
        assertEquals(IrisVariety.SETOSA, testData.irisVariety);
    }

    @Test
    void test_classifier_should_classify_for_double() {
        classifier.setCategoryField("aDouble");
        classifier.setCategoryForData(testData, "12.1");
        assertEquals(12.1, testData.aDouble);
    }
}