package app.models.algorithm.classifiers;

import app.models.datas.data.AbstractData;

public interface IClassifier<T extends AbstractData> {
    void classifyAllData();

    void classifyData(T data);

    void setCategoryForData(T dataToClassify, String categoryOfWorking);
}
