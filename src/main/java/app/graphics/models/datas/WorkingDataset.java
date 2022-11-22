package app.graphics.models.datas;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.algorithm.normalizers.IDataNormalizer;
import app.graphics.models.Observable;
import app.graphics.models.Observer;
import app.graphics.models.datas.data.AbstractData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkingDataset<T extends AbstractData> extends AbstractDataset<T> implements Observable {
    protected String categoryField;
    protected List<String> distanceFields;
    protected List<KNNAlgorithm<T>> algorithms = new ArrayList<>();
    protected ReferenceDataset<T> referenceDataset;
    protected List<Observer> observers = new ArrayList<>();
    private boolean normalized = false;

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField,
                          List<String> distanceFields) {
        super(title, datas);
        this.categoryField = categoryField;
        this.distanceFields = distanceFields;
        this.referenceDataset = referenceDataset;
        this.normalizeDatas();
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

    @Override
    public void addData(T data) {
        super.addData(data);
        if(normalized) IDataNormalizer.normalize(data, this.referenceDataset.getDeltas());
    }

    public boolean isNormalized() { return this.normalized; }

    public List<KNNAlgorithm<T>> getAlgorithms() {
        return this.algorithms;
    }

    public String getCategoryField() {
        return categoryField;
    }

    public List<String> getDistanceFields() {
        return distanceFields;
    }

    public void createAlgorithm(int k, boolean autoClassify) {
        AlgorithmFactory.createKNN(this, k, autoClassify);
        this.updateObservers(this.getLastAlgorithm());
    }

    public void createAlgorithm(int k) {
        this.createAlgorithm(k, false);
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

    public Map<String, List<T>> getWorkingDataByCategories() {
        return super.getDataByCategories(this.categoryField);
    }

    public Map<String, List<T>> getBothDataByCategories() {
        Map<String, List<T>> workingDataByCategories = this.getWorkingDataByCategories();
        this.referenceDataset.getDataByCategories(this.categoryField).forEach((key, value) -> {
            workingDataByCategories.merge(key, value, (list1, list2) -> {
                List<T> mergedLists = new ArrayList<>(list1);
                mergedLists.addAll(list2);
                return mergedLists;
            });
        });
        return workingDataByCategories;
    }

    public boolean canChangeNormalize() { return this.referenceDataset != null && this.hasData() && this.referenceDataset.hasData(); }

    public boolean normalizeDatas() {
        if(this.normalized || !this.canChangeNormalize()) return false;
        this.getDatas().forEach(data -> IDataNormalizer.normalize(data, this.referenceDataset.getDeltas()));
        this.referenceDataset.getDatas().forEach(data -> IDataNormalizer.normalize(data, this.referenceDataset.getDeltas()));
        this.normalized = true;
        return true;
    }

    public boolean unNormalizeDatas() {
        if(!this.normalized || !this.canChangeNormalize()) return false;
        this.getDatas().forEach(data -> IDataNormalizer.denormalize(data, this.referenceDataset.getDeltas()));
        this.referenceDataset.getDatas().forEach(data -> IDataNormalizer.denormalize(data, this.referenceDataset.getDeltas()));
        this.normalized = false;
        return true;
    }
}