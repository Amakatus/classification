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
        this.classifyDataByEntry(this.algorithm.getKNNOfData(data));
    }

    // Get an entry with as key the data to classify and as value the list of its nearest neighbors.
    private void classifyDataByEntry(Entry<T, List<T>> entryToClassify) {
        Map<String, Integer> rates = new HashMap<>();
        T dataToClassify = entryToClassify.getKey();
        List<T> neighbours = entryToClassify.getValue();
        // For each neighbours, increment in a map its category.
        for (T neighbour : neighbours) {
            rates.merge(ClassUtils.getValueObjectFromField(neighbour, this.categoryField).toString(), 1, Integer::sum);
        }
        String categoryOfWorking = getCategoryFromNeighbours(rates);
        setCategoryForData(dataToClassify, categoryOfWorking);
    }

    // Retrieve the most common category from map constructed from nearest neighbors.
    private String getCategoryFromNeighbours(Map<String, Integer> rates) {
        return rates.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .collect(Collectors.toList()).get(0).getKey();
    }
}
