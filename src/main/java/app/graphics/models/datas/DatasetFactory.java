package app.graphics.models.datas;

import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import app.utils.CSVUtils;

public interface DatasetFactory {
    static <T extends Data> ReferenceDataset<T> createReferenceDataset(String title, String path, DataType type){
        return new ReferenceDataset<T>(title, CSVUtils.loadCSV(path, type));
    }

    static ReferenceDataset<IrisData> irisReferenceDataset(String title) {
        return createReferenceDataset(title, "/data/iris.csv", DataType.IRIS);
    }

    static ReferenceDataset<TitanicPassengerData> titanicPassengerReferenceDataset(String title) {
        return createReferenceDataset(title, "/data/titanic.csv", DataType.PASSENGER);
    }
}
