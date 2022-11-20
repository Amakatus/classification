package app.algorithm;

import app.graphics.models.datas.DatasetClassifier;
import app.graphics.models.datas.ReferenceDataset;
import app.graphics.models.datas.WorkingDataset;
import app.graphics.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNNStrength<T extends AbstractData> {
    protected KNNAlgorithm<T> algorithm;
    protected double strength;

    public KNNStrength(KNNAlgorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    public KNNAlgorithm<T> getAlgorithm() {
        return this.algorithm;
    }

    public void calculStrenght() {
        ReferenceDataset<T> rDS = this.algorithm.getReferenceDataset();
        Collections.shuffle(rDS.getDatas());
        WorkingDataset<T> wDS = this.algorithm.workingDataset;
        wDS.clearData();
        List<T> saveDatas = new ArrayList<>();
        int size = 5;
        for (int cpt = 0; cpt < size; cpt++) {
            wDS.addData(this.algorithm.getReferenceDataset().getDatas().get(cpt));
            rDS.removeData(this.algorithm.getReferenceDataset().getDatas().get(cpt));
            saveDatas.add(this.algorithm.getReferenceDataset().getDatas().get(cpt));
        }
        DatasetClassifier<T> dC = new DatasetClassifier<>(this.algorithm);
        dC.classifyDatas();
        int goodClassify = 0;
        for (int cpt = 0; cpt < wDS.getDatas().size(); cpt++) {
            T data = saveDatas.get(cpt);
            T realData = wDS.getDatas().get(cpt);
            Object category = ClassUtils.getValueObjectFromField(data, wDS.getCategoryField());
            Object realCategory = ClassUtils.getValueObjectFromField(realData, wDS.getCategoryField());
            if (category.toString().equals(realCategory.toString())) {
                goodClassify++;
            }
        }
        this.strength = ((double) goodClassify / wDS.getDatas().size()) * 100.0;
    }

    public double getStrength() {
        return this.strength;
    }

}