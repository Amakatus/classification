package app;

import app.models.AbstractModel;
import app.models.Observer;
import app.models.datas.DatasetFactory;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;
import app.models.datas.data.DataType;

import java.util.ArrayList;
import java.util.List;

public class App extends AbstractModel {
    // Singleton
    private static App instance = null;

    public static App getInstance() {
        if (App.instance == null)
            App.instance = new App();
        return App.instance;
    }

    // Classe
    protected List<WorkingDataset<? extends AbstractData>> workingDatasets;
    protected List<ReferenceDataset<? extends AbstractData>> referenceDatasets;

    private App() {
        super();
        this.workingDatasets = new ArrayList<>();
        this.referenceDatasets = new ArrayList<>();
        this.loadReferenceDatasets();
    }

    public List<WorkingDataset<? extends AbstractData>> getWorkingDatasets() {
        return this.workingDatasets;
    }

    public void addWorkingDataset(WorkingDataset<? extends AbstractData> dataset) {
        this.workingDatasets.add(dataset);
        this.updateObservers(dataset);
    }

    public void addWorkingDataset(WorkingDataset<?>... datasets) {
        for (WorkingDataset<?> dataset : datasets) {
            this.addWorkingDataset(dataset);
        }
    }

    public List<ReferenceDataset<? extends AbstractData>> getReferenceDatasets() {
        return this.referenceDatasets;
    }

    public void addReferenceDataset(ReferenceDataset<? extends AbstractData> dataset) {
        this.referenceDatasets.add(dataset);
        this.updateObservers(dataset);
    }

    public void clearWorkingDatasets() {
        this.workingDatasets.clear();
    }

    public void clearReferenceDatasets() {
        this.referenceDatasets.clear();
    }

    public void loadReferenceDatasets() {
        for (DataType type : DataType.values()) {
            this.addReferenceDataset(DatasetFactory.createReferenceDataset(String.format("ReferenceDataset%s", type), type));
        }
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateObservers(Object object) {
        this.observers.forEach(observer -> observer.sendUpdate(object));
    }
}