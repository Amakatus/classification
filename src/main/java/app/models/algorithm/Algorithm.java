package app.models.algorithm;

import app.models.AbstractModel;
import app.models.algorithm.calculators.AbstractCalculator;
import app.models.algorithm.calculators.DistanceCalculator;
import app.models.algorithm.classifiers.AbstractClassifier;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.algorithm.calculators.StrengthCalculator;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public abstract class Algorithm<T extends AbstractData> extends AbstractModel {
    protected WorkingDataset<T> workingDataset;
    protected AbstractClassifier<T> classifier;
    protected AbstractCalculator<T> calculator;
    protected StrengthCalculator<T> strength;

    protected Algorithm(WorkingDataset<T> workingDataset, boolean autoClassify, IGeometryCalculator<T> geometry){
        this.workingDataset = workingDataset;
        this.classifier = null;
        this.calculator = new DistanceCalculator<>(this, geometry);
        if (autoClassify) {
            this.classifyWorkingDataset();
            this.generateStrength();
        }
    }

    public WorkingDataset<T> getWorkingDataset() {
        return workingDataset;
    }

    public ReferenceDataset<T> getReferenceDataset() {
        return workingDataset.getReferenceDataset();
    }

    public void classifyWorkingDataset() {
        this.classifier.classifyAllData();
    }

    public void generateStrength() {
        this.strength.calculStrenght();
    }

    public AbstractClassifier<T> getClassifier() {
        return this.classifier;
    }

    public AbstractCalculator<T> getCalculator() { return calculator; }
}
