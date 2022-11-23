package app.models.algorithm.classifiers;

import app.models.algorithm.KNNAlgorithm;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;
import app.models.datas.data.IrisVariety;
import app.utils.ClassUtils;
import app.utils.LoggerUtils;
import app.utils.ProjectUtils;

import java.lang.reflect.Field;

public abstract class AbstractClassifier<T extends AbstractData> implements IClassifier<T> {
    protected WorkingDataset<T> workingDataset;
    protected String categoryField;

    public AbstractClassifier(WorkingDataset<T> workingDataset) {
        this.workingDataset = workingDataset;
        this.categoryField = workingDataset.getCategoryField();
    }

    public AbstractClassifier(KNNAlgorithm<T> algorithm) {
        this.workingDataset = algorithm.getWorkingDataset();
        this.categoryField = this.workingDataset.getCategoryField();
    }

    public void setCategoryField(String categoryField) {
        this.categoryField = categoryField;
    }

    @Override
    public void classifyAllData() {
        this.workingDataset.getData().forEach(this::classifyData);
    }

    @Override
    public void setCategoryForData(T dataToClassify, String categoryOfWorking) {
        try {
            Field field = ClassUtils.getFieldByName(dataToClassify, categoryField);
            if (field == null) return;
            Class<?> fieldType = field.getType();
            if (fieldType.isAssignableFrom(IrisVariety.class)) {
                field.set(dataToClassify, IrisVariety.valueOf(categoryOfWorking));
            } else if (fieldType.isAssignableFrom(double.class)) {
                field.set(dataToClassify, Double.valueOf(categoryOfWorking));
            } else if (fieldType.isAssignableFrom(String.class)) {
                field.set(dataToClassify, categoryOfWorking);
            } else if (fieldType.equals(boolean.class)) {
                field.set(dataToClassify, ProjectUtils.stringToDouble(categoryOfWorking));
            } else {
                LoggerUtils.log("Unsupported type : " + fieldType);
            }
        } catch (Exception e) {
            LoggerUtils.exception(e);
        }
    }
}
