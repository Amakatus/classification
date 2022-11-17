package app.graphics.models.datas;

import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import app.utils.CSVUtils;

public interface DatasetFactory {
    static ReferenceDataset<IrisData> irisReferenceDataset(String title) {
        return new ReferenceDataset<>(title, CSVUtils.loadCSV("/data/iris.csv", DataType.IRIS));
    }

    static ReferenceDataset<TitanicPassengerData> titanicPassengerReferenceDataset(String title) {
        return new ReferenceDataset<>(title, CSVUtils.loadCSV("/data/titanic.csv", DataType.PASSENGER));
    }
}
