package app.graphics.models.datas;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.AbstractData;
import app.graphics.models.datas.data.IrisVariety;
import app.utils.ClassUtils;
import app.utils.Logger;
import app.utils.ProjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DatasetClassifier<T extends AbstractData> {
    protected WorkingDataset<T> workingDS;
    protected KNNAlgorithm<T> algorithm;
    protected String categoryField;

    public DatasetClassifier(KNNAlgorithm<T> algorithm) {
        this.algorithm = algorithm;
        this.workingDS = algorithm.getWorkingDataset();
    }

    public void classifyDatas() {
        this.categoryField = this.workingDS.getCategoryField();
        List<Entry<T, List<T>>> workingsAndNeighbours = this.algorithm.getDatasKNN();
        for (Entry<T, List<T>> entryToClassify : workingsAndNeighbours) {
            this.classifyData(entryToClassify);
        }
    }

    protected void classifyData(Entry<T, List<T>> entryToClassify) {
        Map<String, Integer> rates = new HashMap<>();
        T dataToClassify = entryToClassify.getKey();
        List<T> neighbours = entryToClassify.getValue();
        for (T neighbour : neighbours) {
            rates.merge(ClassUtils.getValueObjectFromField(neighbour, this.categoryField).toString(), 1, Integer::sum);
        }
        String categoryOfWorking = getCategoryFromNeighbours(rates);
        setCategoryForData(dataToClassify, categoryOfWorking);
    }

    private String getCategoryFromNeighbours(Map<String, Integer> rates) {
        return rates.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .limit(1)
                .collect(Collectors.toList()).get(0).getKey();
    }

    private void setCategoryForData(T dataToClassify, String categoryOfWorking) {
        try {
            Field field = ClassUtils.getFieldByName(dataToClassify, categoryField);
            if (field == null) return;
            Class<?> fieldType = field.getType();
            if (fieldType.isAssignableFrom(IrisVariety.class)) {
                field.set(dataToClassify, IrisVariety.valueOf(categoryOfWorking));
            } else if (fieldType.isAssignableFrom(double.class)) {
                field.set(dataToClassify, Double.valueOf(categoryOfWorking));
            } else if (fieldType.isAssignableFrom(String.class)) {
                field.set(dataToClassify, categoryOfWorking);
            } else if (fieldType.equals(boolean.class)) {
                field.set(dataToClassify, ProjectUtils.stringToDouble(categoryOfWorking));
            } else {
                Logger.log("Unsupported type : " + fieldType);
            }
        } catch (Exception e) {
            Logger.exception(e);
        }
    }
}