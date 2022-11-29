package app.models.datas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.models.Observable;
import app.models.Observer;
import app.models.datas.data.AbstractData;
import app.utils.ClassUtils;

public abstract class AbstractDataset<T extends AbstractData> implements Observable {
    protected String title;
    protected List<T> data;
    protected List<Observer> observers = new ArrayList<>();

    protected AbstractDataset(String title, List<T> data) {
        this.title = title;
        this.data = data == null ? new ArrayList<>() : data;
    }

    public String getTitle() {
        return this.title;
    }

    public List<T> getData() {
        return this.data;
    }

    public void addData(T data) {
        this.data.add(data);
    }

    public void removeData(T data) {
        this.data.remove(data);
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Map<String, List<T>> getDataByCategories(String categoryField) {
        Map<String, List<T>> res = new HashMap<>();
        this.getData().forEach(data -> {
            Object category = ClassUtils.getValueObjectFromField(data, categoryField);
            if (category != null)
                res.computeIfAbsent(category.toString(), create -> new ArrayList<>()).add(data);
        });
        return res;
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

    public double getDataSize() {
        return this.data.size();
    }

    public boolean hasData() {
        return !this.data.isEmpty();
    }

    public String toString() {
        return String.format("%s", this.title);
    }

}