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
    private static final int GROUP_SIZE = 5;

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
        this.groupsToTest = this.generateGroups();
        List<Double> groupStrengths = new ArrayList<>();
        this.groupsToTest.forEach(tWorkingDataset -> {
            List<String> savedDataCategories = this.saveDataCategories(tWorkingDataset);
            Collections.shuffle(tWorkingDataset.getReferenceDataset().getData());
            tWorkingDataset.createKNN(this.algorithm.getKNeighbours(), false);
            tWorkingDataset.getLastAlgorithm().classifyWorkingDataset();
            groupStrengths.add(generateGroupStrength(tWorkingDataset, savedDataCategories));
        });
        this.strength = Math.round(groupStrengths.stream().mapToDouble(score -> score).average().orElse(0));
    }

    private double generateGroupStrength(WorkingDataset<T> tWorkingDataset, List<String> savedDataCategories) {
        int goodClassify = 0;
        for (int i = 0; i < savedDataCategories.size(); i++) {
            Object classifiedCategory = ClassUtils.getValueObjectFromField(tWorkingDataset.getData().get(i), tWorkingDataset.getCategoryField());
            String realCategory = savedDataCategories.get(i);
            if (classifiedCategory.toString().equals(realCategory)) {
                goodClassify++;
            }
        }
        return ((double) (goodClassify / tWorkingDataset.getData().size()) * 100.0);
    }

    private List<String> saveDataCategories(WorkingDataset<T> tWorkingDataset) {
        List<String> savedCategories = new ArrayList<>();
        tWorkingDataset.getData().forEach(data -> {
            savedCategories.add(ClassUtils.getValueObjectFromField(data, tWorkingDataset.getCategoryField()).toString());
        });
        return savedCategories;
    }

    private List<WorkingDataset<T>> generateGroups() {
        List<WorkingDataset<T>> res = new ArrayList<>();
        String categoryField = this.initialWorkingDataset.getCategoryField();
        List<String> distanceFields = this.initialWorkingDataset.getDistanceFields();
        int sizeToTest = this.initialReferenceDataset.getData().size();
        int groupSize = StrengthCalculator.GROUP_SIZE;
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
        return res;
    }

    public double getStrength() {
        return this.strength;
    }

    public List<WorkingDataset<T>> getGroupsToTest() {
        return this.groupsToTest;
    }
}