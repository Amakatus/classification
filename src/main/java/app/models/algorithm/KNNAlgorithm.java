package app.models.algorithm;

import app.models.algorithm.classifiers.KNNDistanceClassifier;
import app.models.algorithm.geometry.EuclideanGeometry;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.algorithm.calculators.StrengthCalculator;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class KNNAlgorithm<T extends AbstractData> extends Algorithm<T> {
    protected int kNeighbours;

    protected KNNAlgorithm(WorkingDataset<T> workingDataset, int k, boolean autoClassify, IGeometryCalculator<T> geometry){
        super(workingDataset, geometry);
        this.kNeighbours = k;
        this.strengthCalculator = new StrengthCalculator<>(this);
        this.classifier = new KNNDistanceClassifier<>(this);
        if (autoClassify) {
            this.classifyWorkingDataset();
            this.generateStrength();
        }
    }

    protected KNNAlgorithm(WorkingDataset<T> workingDataset, int k, boolean autoClassify) {
        this(workingDataset, k, autoClassify, new EuclideanGeometry<>(workingDataset.getDistanceFields()));
    }

    public int getKNeighbours() {
        return kNeighbours;
    }

    public Entry<T, List<T>> getKNNOfData(IGeometryCalculator<T> geometry, T data) {
        return getKNNOfDataByDistances(this.calculator.getDistances(data));
    }

    public Entry<T, List<T>> getKNNOfData(T data) {
        return getKNNOfData(new EuclideanGeometry<>(this.workingDataset.getDistanceFields()), data);
    }

    private Entry<T, List<T>> getKNNOfDataByDistances(Map<T, Double> dataWithDistances) {
        List<Entry<T, Double>> sortedDataEntries = dataWithDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(this.kNeighbours + 1L)
                .collect(Collectors.toList());

        // Using the sorted list to generate the Entry with as key the working data
        // and as value the list of knn for this working data.
        List<T> neighbours = new ArrayList<>();
        for (int i = 1; i < sortedDataEntries.size(); i++)
            neighbours.add(sortedDataEntries.get(i).getKey());

        return Map.entry(sortedDataEntries.get(0).getKey(), neighbours);
    }

    public String toString() {
        return this.kNeighbours + "NNAlgorithm "+this.calculator.getGeometry()+"(" + this.getStrength() + "%)";
    }
}