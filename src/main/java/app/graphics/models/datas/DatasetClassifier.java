package app.graphics.models.datas;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.IrisVariety;
import app.utils.ClassUtils;

public class DatasetClassifier<T extends Data> {
	protected WorkingDataset<T> workingDS;
	protected KNNAlgorithm<T> algorithm;
	protected String categoryField;

	protected DatasetClassifier(KNNAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
		this.workingDS = algorithm.getWorkingDataset();
	}
	
	protected void classifyDatas() {
		this.categoryField = this.workingDS.getCategoryField();
		List<Entry<T, List<T>>> workingsAndNeighbours = this.algorithm.getDatasKNN();
		for(Entry<T, List<T>> entryToClassify : workingsAndNeighbours) {
			this.classifyData(entryToClassify);
		}
	}
	
	protected void classifyData(Entry<T, List<T>> entryToClassify) {
		Map<String, Integer> rates = new HashMap<>();
		T dataToClassify = entryToClassify.getKey();
		List<T> neighbours;
		String categoryOfNeighbour;
		dataToClassify = entryToClassify.getKey();
		neighbours = entryToClassify.getValue();
		for(T neighbour : neighbours) {
			categoryOfNeighbour = ClassUtils.getValueObjectFromField(neighbour, this.categoryField).toString();
			rates.merge(categoryOfNeighbour, 1, Integer::sum);
		}
		String categoryOfWorking = rates.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.limit(1)
				.collect(Collectors.toList()).get(0).getKey();
		System.out.println("Old value : " + ClassUtils.getValueObjectFromField(dataToClassify, this.categoryField));
		System.out.println(dataToClassify + " should be " + categoryOfWorking);
		try {
			Field field = ClassUtils.getFieldByName(dataToClassify, categoryField);
			field.setAccessible(true);
			Class<?> fieldType = field.getType();
			if(fieldType.isAssignableFrom(IrisVariety.class)) {
				field.set(dataToClassify, IrisVariety.valueOf(categoryOfWorking));
			} else {
				System.err.println("Unsupported type : " + fieldType.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("New value : " + ClassUtils.getValueObjectFromField(dataToClassify, this.categoryField));
	}
}
