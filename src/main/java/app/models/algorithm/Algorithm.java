package app.models.algorithm;

import app.models.AbstractModel;
import app.models.algorithm.calculators.AbstractCalculator;
import app.models.algorithm.calculators.DistanceCalculator;
import app.models.algorithm.calculators.RandomDistanceCalculator;
import app.models.algorithm.calculators.StrengthCalculator;
import app.models.algorithm.classifiers.AbstractClassifier;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public abstract class Algorithm<T extends AbstractData> extends AbstractModel {
    protected WorkingDataset<T> workingDataset;
    protected StrengthCalculator<T> strengthCalculator;
    protected AbstractClassifier<T> classifier;
    protected AbstractCalculator<T> calculator;

    protected Algorithm(WorkingDataset<T> workingDataset, IGeometryCalculator<T> geometry) {
        this.workingDataset = workingDataset;
        this.calculator = new RandomDistanceCalculator<>(this, geometry);
    }

    public StrengthCalculator<T> getStrengthCalculator() {
        return this.strengthCalculator;
    }

    public double getStrength() {
        return this.strengthCalculator.getStrength();
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
        this.strengthCalculator.calculStrenght();
    }

    public AbstractClassifier<T> getClassifier() {
        return this.classifier;
    }

    public AbstractCalculator<T> getCalculator() {
        return calculator;
    }
}
