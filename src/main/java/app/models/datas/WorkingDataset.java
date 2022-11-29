package app.models.datas;

import app.models.algorithm.AbstractAlgorithm;
import app.models.algorithm.AlgorithmFactory;
import app.models.algorithm.KNNAlgorithm;
import app.models.algorithm.calculators.DistanceCalculator;
import app.models.algorithm.calculators.ICalculator;
import app.models.algorithm.geometry.EuclideanGeometry;
import app.models.algorithm.geometry.IGeometry;
import app.models.datas.data.AbstractData;
import app.models.datas.data.normalizers.IDataNormalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkingDataset<T extends AbstractData> extends AbstractDataset<T> {
    protected String categoryField;
    protected List<String> distanceFields;
    protected List<AbstractAlgorithm<T>> algorithms = new ArrayList<>();
    protected ReferenceDataset<T> referenceDataset;
    private boolean normalized = false;

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset, String categoryField,
                          List<String> distanceFields) {
        super(title, datas);
        this.categoryField = categoryField;
        this.distanceFields = distanceFields;
        this.referenceDataset = referenceDataset;
        this.normalizeDatas();
    }

    public WorkingDataset(String title, List<T> datas, ReferenceDataset<T> referenceDataset) {
        this(title, datas, referenceDataset, null, new ArrayList<>());
    }

    public WorkingDataset(List<T> datas, ReferenceDataset<T> referenceDataset) {
        this("title", datas, referenceDataset);
    }

    public WorkingDataset(ReferenceDataset<T> referenceDataset) {
        this("title", new ArrayList<>(), referenceDataset);
    }

    @Override
    public void addData(T data) {
        super.addData(data);
        if (normalized) IDataNormalizer.normalize(data, this.referenceDataset.getDeltas());
    }

    public boolean isNormalized() {
        return this.normalized;
    }

    public void setReferenceDataset(ReferenceDataset<T> referenceDataset){
        this.referenceDataset = referenceDataset;
    }

    public List<AbstractAlgorithm<T>> getAlgorithms() {
        return this.algorithms;
    }

    public String getCategoryField() {
        return categoryField;
    }

    public List<String> getDistanceFields() {
        return distanceFields;
    }

    public KNNAlgorithm<T> createKNN(int k, boolean autoClassify, ICalculator<T> calculator) {
        AlgorithmFactory.createKNN(this, k, autoClassify, calculator);
        this.updateObservers(this.getLastAlgorithm());
        return (KNNAlgorithm<T>) this.getLastAlgorithm();
    }

    public KNNAlgorithm<T> createKNN(int k, boolean autoClassify, IGeometry<T> geometryCalculator) {
        return createKNN(k, autoClassify, new DistanceCalculator<>(this.referenceDataset, geometryCalculator));
    }

    public KNNAlgorithm<T> createKNN(int k, boolean autoClassify) {
        return createKNN(k, autoClassify, new EuclideanGeometry<>(this.distanceFields));
    }

    public KNNAlgorithm<T> createKNN(int k, IGeometry<T> geometryCalculator) {
        return createKNN(k, false, geometryCalculator);
    }

    public KNNAlgorithm<T> createKNN(int k) {
        return this.createKNN(k, false);
    }

    public AbstractAlgorithm<T> getLastAlgorithm() {
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

    public void setCategoryField(String categoryFieldName) {
        this.categoryField = categoryFieldName;
    }

    public ReferenceDataset<T> getReferenceDataset() {
        return this.referenceDataset;
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

    public boolean canChangeNormalize() {
        return this.referenceDataset == null || !this.hasData() || !this.referenceDataset.hasData();
    }

    public boolean normalizeDatas() {
        if (this.normalized || this.canChangeNormalize()) return false;
        this.getData().forEach(data -> IDataNormalizer.normalize(data, this.referenceDataset.getDeltas()));
        this.referenceDataset.getData().forEach(data -> IDataNormalizer.normalize(data, this.referenceDataset.getDeltas()));
        this.normalized = true;
        return true;
    }

    public boolean unNormalizeDatas() {
        if (!this.normalized || this.canChangeNormalize()) return false;
        this.getData().forEach(data -> IDataNormalizer.denormalize(data, this.referenceDataset.getDeltas()));
        this.referenceDataset.getData().forEach(data -> IDataNormalizer.denormalize(data, this.referenceDataset.getDeltas()));
        this.normalized = false;
        return true;
    }
}