package app.models.algorithm.calculators;

import app.models.algorithm.KNNAlgorithm;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrengthCalculator<T extends AbstractData> {

    protected KNNAlgorithm<T> algorithm;
    protected WorkingDataset<T> initialWorkingDataset;
    protected ReferenceDataset<T> initialReferenceDataset;
    protected List<WorkingDataset<T>> groupsToTest;
    protected double strength;

    public StrengthCalculator(KNNAlgorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    public KNNAlgorithm<T> getAlgorithm() {
        return this.algorithm;
    }

    public void calculStrenght() {
        initialWorkingDataset = algorithm.getWorkingDataset();
        initialReferenceDataset = algorithm.getReferenceDataset();
        System.out.println("Strength calculation launched with groups of " + algorithm.getKNeighbors() + " data for dataset : " + initialWorkingDataset.getTitle());
        Collections.shuffle(initialReferenceDataset.getData());
        this.groupsToTest = this.generateGroups();
        List<Double> groupStrengths = new ArrayList<>();
        int groupTested = 0;
        for(WorkingDataset<T> tWorkingDataset : groupsToTest){
            List<String> savedDataCategories = this.saveDataCategories(tWorkingDataset);
            KNNAlgorithm<T> createdAlgorithm = tWorkingDataset.createKNN(this.algorithm.getKNeighbors(), false, this.algorithm.getCalculator().getGeometry());
            createdAlgorithm.classifyWorkingDataset();
            System.out.println("Launch of strength test for group : " + groupTested);
            groupStrengths.add(generateGroupStrength(tWorkingDataset, savedDataCategories));
            groupTested++;
        }

        this.strength = Math.round(groupStrengths.stream().mapToDouble(score -> score).average().orElse(0));
        System.out.println("End of the strength test, strength : " + this.strength);
    }

    private double generateGroupStrength(WorkingDataset<T> tWorkingDataset, List<String> savedDataCategories) {
        double goodClassify = 0;
        for (int i = 0; i < savedDataCategories.size(); i++) {
            T currentData = tWorkingDataset.getData().get(i);
            Object classifiedCategory = ClassUtils.getValueObjectFromField(currentData, tWorkingDataset.getCategoryField());
            String realCategory = savedDataCategories.get(i);
            if (classifiedCategory.toString().equals(realCategory)) {
                goodClassify++;
            }
            algorithm.getClassifier().setCategoryForData(currentData, realCategory);
        }
        System.out.println("Got " + goodClassify + " good data classified out of " + tWorkingDataset.getData().size() + " strength = " + (goodClassify / tWorkingDataset.getData().size()) * 100.0);
        return (goodClassify / tWorkingDataset.getData().size()) * 100.0;
    }

    private List<String> saveDataCategories(WorkingDataset<T> tWorkingDataset) {
        List<String> savedCategories = new ArrayList<>();
        tWorkingDataset.getData().forEach(data -> {
            savedCategories.add(ClassUtils.getValueObjectFromField(data, tWorkingDataset.getCategoryField()).toString());
        });
        return savedCategories;
    }

    private List<WorkingDataset<T>> generateGroups() {
        System.out.println("Started generation of groups...");
        List<WorkingDataset<T>> res = new ArrayList<>();
        String categoryField = this.initialWorkingDataset.getCategoryField();
        List<String> distanceFields = this.initialWorkingDataset.getDistanceFields();
        int sizeToTest = this.initialReferenceDataset.getData().size();
        int groupSize = this.algorithm.getKNeighbors();
        int offset = 0;
        while (sizeToTest != 0) {
            WorkingDataset<T> newGroup = new WorkingDataset<>("GroupToTest", new ArrayList<>(), this.initialReferenceDataset, categoryField, distanceFields);
            if (sizeToTest < groupSize) groupSize = sizeToTest;
            for (int i = 0; i < groupSize; i++) {
                newGroup.addData(this.initialReferenceDataset.getData().get(offset));
                offset++;
                sizeToTest--;
            }
            res.add(newGroup);
        }
        System.out.println(res.size() + " groups generated.");
        return res;
    }

    public double getStrength() {
        return this.strength;
    }

    public List<WorkingDataset<T>> getGroupsToTest() {
        return this.groupsToTest;
    }
}