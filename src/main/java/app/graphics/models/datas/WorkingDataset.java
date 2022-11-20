package app.graphics.models.datas;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.Observable;
import app.graphics.models.Observer;
import app.graphics.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkingDataset<T extends AbstractData> extends AbstractDataset<T> implements Observable {
    protected String categoryField;
    protected List<String> distanceFields;
    protected List<KNNAlgorithm<T>> algorithms;
    protected ReferenceDataset<T> referenceDataset;
    protected List<Observer> observers;

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField,
                          List<String> distanceFields) {
        super(title, datas);
        this.categoryField = categoryField;
        this.distanceFields = distanceFields;
        this.algorithms = new ArrayList<>();
        this.referenceDataset = referenceDataset;
        this.observers = new ArrayList<>();
    }

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField) {
        this(title, datas, referenceDataset, categoryField, new ArrayList<>());
    }

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset) {
        this(title, datas, referenceDataset, null);
    }

    public WorkingDataset(String title, ReferenceDataset<T> referenceDataset) {
        this(title, new ArrayList<>(), referenceDataset);
    }

    public List<KNNAlgorithm<T>> getAlgorithms() {
        return this.algorithms;
    }

    public String getCategoryField() {
        return categoryField;
    }

    public List<String> getDistanceFields() {
        return distanceFields;
    }

    public void createAlgorithm(int k) {
        AlgorithmFactory.createKNN(this, k);
        this.updateObservers(this.getLastAlgorithm());
    }

    public KNNAlgorithm<T> getLastAlgorithm() {
        return this.algorithms.get(this.algorithms.size() - 1);
    }

    public void addAlgorithm(KNNAlgorithm<T> algorithm) {
        this.algorithms.add(algorithm);
    }

    public void addDistanceFieldString(String distanceFieldName) {
        if (!this.distanceFields.contains(distanceFieldName)) {
            this.distanceFields.add(distanceFieldName);
        }
    }

    public void removeDistanceFieldString(String distanceFieldName) {
        this.distanceFields.remove(distanceFieldName);
    }

    public void setCategoryField(String categoryFieldName) {
        this.categoryField = categoryFieldName;
    }

    public ReferenceDataset<T> getReferenceDataset() {
        return this.referenceDataset;
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateObservers(Object object) {
        this.observers.forEach(observer -> observer.sendUpdate(object));
    }

    public Map<String, List<T>> getDataByCategories() {
        Map<String, List<T>> res = new HashMap<>();
        this.getDatas().forEach(data -> {
            Object category = ClassUtils.getValueObjectFromField(data, this.categoryField);
            if (category != null)
                res.computeIfAbsent(category.toString(), create -> new ArrayList<>()).add(data);
        });
        return res;
    }
}