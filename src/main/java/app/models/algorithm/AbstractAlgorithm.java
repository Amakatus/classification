package app.models.algorithm;

import app.models.AbstractModel;
import app.models.algorithm.calculators.CalculatorFactory;
import app.models.algorithm.calculators.ICalculator;
import app.models.algorithm.calculators.StrengthCalculator;
import app.models.algorithm.classifiers.AbstractClassifier;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

public abstract class AbstractAlgorithm<T extends AbstractData> extends AbstractModel {
    protected WorkingDataset<T> workingDataset;
    protected StrengthCalculator<T> strengthCalculator;
    protected AbstractClassifier<T> classifier;
    protected ICalculator<T> calculator;

    protected AbstractAlgorithm(WorkingDataset<T> workingDataset, ICalculator<T> calculator) {
        this.workingDataset = workingDataset;
        this.calculator = calculator;
    }

    public void setCalculator(ICalculator<T> calculator){
        this.calculator = calculator;
        this.classifyWorkingDataset();
        this.generateStrength();
    }

    public void setCalculator(String calculator) {
        setCalculator(CalculatorFactory.createCalculator(calculator, this.getReferenceDataset(), this.calculator.getGeometry()));
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
