package app.graphics.models.datas;

import java.util.List;

import app.graphics.models.datas.data.Data;

public class ReferenceDataset<T extends Data> extends Dataset<T> {

	public ReferenceDataset(String title, List<T> datas) {
		super(title, datas);
	}
	
	public ReferenceDataset(String title) {
		this(title, null);
	}
}