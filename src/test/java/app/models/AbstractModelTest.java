package app.models;

import app.controllers.IndexController;
import app.models.algorithm.KNNAlgorithm;
import app.models.datas.DatasetFactory;
import app.models.datas.WorkingDataset;
import app.models.datas.data.IrisData;
import app.utils.ClassUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractModelTest {
    KNNAlgorithm<IrisData> model;
    WorkingDataset<IrisData> workingDataset;

    public AbstractModelTest() {
        workingDataset = new WorkingDataset<>(List.of(), DatasetFactory.irisReferenceDataset("Test"));
        this.model = workingDataset.createKNN(3, false);
    }

    @Test
    void test_model_should_be_able_to_attach_observer() {
        assertTrue(model.observers.isEmpty());
        model.attach(new IndexController());
        assertEquals(1, model.observers.size());
    }

    @Test
    void test_model_should_be_able_to_detach_observer() {
        IndexController controller = new IndexController();
        model.attach(controller);
        model.detach(controller);
        assertTrue(model.observers.isEmpty());
    }
}