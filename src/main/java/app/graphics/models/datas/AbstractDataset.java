package app.graphics.models.datas;

import app.graphics.models.Model;
import app.graphics.models.datas.columns.Column;
import app.graphics.models.datas.data.AbstractData;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataset<T extends AbstractData> implements Model {
	protected String title;
	protected List<Column<T>> columns;
	protected List<T> datas;

	protected AbstractDataset(String title, List<T> datas) {
		this.title = title;
		this.datas = datas == null ? new ArrayList<>() : datas;
		this.columns = new ArrayList<>();
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
	
	public void clearData() {
		this.datas.clear();
	}

	public String toString() {
		return String.format("%s", this.title);
	}
}