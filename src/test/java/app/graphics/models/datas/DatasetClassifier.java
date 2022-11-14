package app.graphics.models.datas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import app.algorithm.AlgorithmFactory;
import app.algorithm.KNNAlgorithm;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.IrisData;
import app.utils.ClassUtils;

public class DatasetClassifier<T extends Data> {
	protected WorkingDataset<T> workingDS;
	protected KNNAlgorithm<T> algorithm;
	protected String categoryField;

	protected DatasetClassifier(KNNAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
		this.workingDS = algorithm.getWorkingDataset();
		this.categoryField = this.workingDS.getCategoryField();
	}
	
	protected void classifyDatas() {
		List<Entry<T, List<T>>> workingsAndNeighbours = this.algorithm.getDatasKNN();
		System.out.println(workingsAndNeighbours.size());
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
			String categoryOfWorking = rates.entrySet().stream()
					.sorted(Map.Entry.comparingByValue())
					.limit(1)
					.collect(Collectors.toList()).get(0).getKey();
			System.out.println(dataToClassify + " should be " + categoryOfWorking);
		}
	}
	
	public static void main(String[] args) {
		ReferenceDataset<IrisData> rDS = DatasetFactory.irisReferenceDataset("rds");
		WorkingDataset<IrisData> wDS = new WorkingDataset<>("wds", rDS);
		wDS.setCategoryField("variety");
		wDS.addDistanceFieldString("petalLength");
		wDS.addDistanceFieldString("petalWidth");
		IrisData irisOne = new IrisData();
		IrisData irisTwo = new IrisData();
		irisOne.setPetalLength(5);
		irisOne.setPetalWidth(1);
		irisTwo.setPetalLength(3);
		irisTwo.setPetalWidth(1.3);
		AlgorithmFactory.createKNN(wDS, 3);
		KNNAlgorithm<IrisData> algo = wDS.getAlgorithms().get(0);
		DatasetClassifier<IrisData> classifier = new DatasetClassifier<>(algo);
		System.out.println("LAUNCHED");
		classifier.classifyDatas();
	}
}
