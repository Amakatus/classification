package app.models.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import app.models.algorithm.calculators.StrengthCalculator;
import app.models.algorithm.classifiers.KNNDistanceClassifier;
import app.models.algorithm.geometry.EuclideanGeometry;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;
import app.models.datas.data.IrisData;

public class KNNAlgorithm<T extends AbstractData> extends Algorithm<T> {
    protected int kNeighbours;

    protected KNNAlgorithm(WorkingDataset<T> workingDataset, int k, boolean autoClassify, IGeometryCalculator<T> geometry) {
        super(workingDataset, geometry);
        this.kNeighbours = k;
        this.strengthCalculator = new StrengthCalculator<>(this);
        this.classifier = new KNNDistanceClassifier<>(this);
        if (autoClassify) {
            this.classifyWorkingDataset();
            //this.generateStrength();
        }
    }

    protected KNNAlgorithm(WorkingDataset<T> workingDataset, int k, boolean autoClassify) {
        this(workingDataset, k, autoClassify, new EuclideanGeometry<>(workingDataset.getDistanceFields()));
    }

    public int getKNeighbors() {
        return kNeighbours;
    }

    public Entry<T, List<T>> getKNNOfData(T data) {
    	// Construct a map, with as key the reference data and as value its distance with the given working data.
        Map<T, Double> dataWithDistances = this.calculator.getDistances(data);
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
        return this.kNeighbours + "NNAlgorithm " + this.calculator.getGeometry() + "(" + this.getStrength() + "%)";
    }
}
