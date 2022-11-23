package app.models.algorithm.classifiers;

import app.models.algorithm.KNNAlgorithm;
import app.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class KNNDistanceClassifier<T extends AbstractData> extends AbstractClassifier<T> {
    protected KNNAlgorithm<T> algorithm;

    public KNNDistanceClassifier(KNNAlgorithm<T> algorithm) {
        super(algorithm);
        this.algorithm = algorithm;
    }

    @Override
    public void classifyData(T data) {
        this.classifyData(this.algorithm.getKNNOfData(data));
    }

    protected void classifyData(Entry<T, List<T>> entryToClassify) {
        Map<String, Integer> rates = new HashMap<>();
        T dataToClassify = entryToClassify.getKey();
        List<T> neighbours = entryToClassify.getValue();
        for (T neighbour : neighbours) {
            System.out.println("Found :" + ClassUtils.getValueObjectFromField(neighbour, this.categoryField).toString());
            rates.merge(ClassUtils.getValueObjectFromField(neighbour, this.categoryField).toString(), 1, Integer::sum);
        }
        String categoryOfWorking = getCategoryFromNeighbours(rates);
        System.out.println("He got " + categoryOfWorking);
        setCategoryForData(dataToClassify, categoryOfWorking);
    }

    // Retrieve the most common category from the k neighbours.
    private String getCategoryFromNeighbours(Map<String, Integer> rates) {
        for(Entry<String, Integer> entry : rates.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return rates.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .collect(Collectors.toList()).get(0).getKey();
    }

}