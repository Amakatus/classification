package app.graphics.models.datas;

import app.graphics.models.datas.data.AbstractData;
import app.utils.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDataset<T extends AbstractData> {
    protected String title;
    protected List<T> datas;

    protected AbstractDataset(String title, List<T> datas) {
        this.title = title;
        this.datas = datas == null ? new ArrayList<>() : datas;
    }

    public String getTitle() {
        return this.title;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void addData(T data) {
        this.datas.add(data);
    }

    public void removeData(T data) {
        this.datas.remove(data);
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public void addData(T... datas) {
        for (T data : datas) {
            this.addData(data);
        }
    }

    public void removeData(T... datas) {
        for (T data : datas) {
            this.removeData(data);
        }
    }

    public Map<String, List<T>> getDataByCategories(String categoryField) {
        Map<String, List<T>> res = new HashMap<>();
        this.getDatas().forEach(data -> {
            Object category = ClassUtils.getValueObjectFromField(data, categoryField);
            if (category != null)
                res.computeIfAbsent(category.toString(), create -> new ArrayList<>()).add(data);
        });
        return res;
    }

    public double getDataSize() {
        return this.datas.size();
    }

    public boolean hasData() {
        return !this.datas.isEmpty();
    }

    public String toString() {
        return String.format("%s", this.title);
    }
}