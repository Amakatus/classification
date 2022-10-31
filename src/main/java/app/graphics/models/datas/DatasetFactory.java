package app.graphics.models.datas;

import app.graphics.models.datas.data.IrisData;
import app.utils.CSVUtils;

public abstract class DatasetFactory {
	public static ReferenceDataset<IrisData> irisReferenceDataset(String title) {
		return new ReferenceDataset<IrisData>(title, CSVUtils.loadIrisCSV());
	}
}
