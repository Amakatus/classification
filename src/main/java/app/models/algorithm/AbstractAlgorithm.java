package app.models.algorithm;

import app.models.AbstractModel;
import app.models.algorithm.calculators.ICalculator;
import app.models.algorithm.calculators.StrengthCalculator;
import app.models.algorithm.classifiers.AbstractClassifier;
import app.models.algorithm.geometry.IGeometry;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public abstract class AbstractAlgorithm<T extends AbstractData> extends AbstractModel {
    protected WorkingDataset<T> workingDataset;
    protected StrengthCalculator<T> strengthCalculator;
    protected AbstractClassifier<T> classifier;
    protected ICalculator<T> calculator;

    protected AbstractAlgorithm(WorkingDataset<T> workingDataset, IGeometry<T> geometry, ICalculator<T> calculator) {
        this.workingDataset = workingDataset;
        this.calculator = calculator;
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

    public ICalculator<T> getCalculator() {
        return calculator;
    }
}
