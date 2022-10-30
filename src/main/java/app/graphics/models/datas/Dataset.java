package app.graphics.models.datas;

import java.util.ArrayList;
import java.util.List;

import app.graphics.models.Model;
import app.graphics.models.datas.columns.Column;
import app.graphics.models.datas.data.Data;

public abstract class Dataset<T extends Data> implements Model {
	protected String title;
	protected List<Column<T>> columns;
	protected List<T> datas;
	
	public Dataset(String title, List<T> datas) {
		this.title = title;
		this.datas = datas;
		this.columns = new ArrayList<Column<T>>();
	}

	public String getTitle() { return this.title; }
	
	public List<T> getDatas() {
		return this.datas;
	}
	
	public void addData(T data) {
		this.datas.add(data);
	}
	
	public String toString() {
		return String.format("%s", this.title);
	}
}