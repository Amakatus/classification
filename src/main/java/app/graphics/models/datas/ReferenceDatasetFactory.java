package app.graphics.models.datas;

import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.data.DataType;
import app.graphics.models.datas.data.IrisData;
import app.graphics.models.datas.data.TitanicPassengerData;
import app.utils.CSVUtils;

public interface ReferenceDatasetFactory {
    static <T extends Data> ReferenceDataset<T> createReferenceDataset(String title, String path, DataType type){
        return new ReferenceDataset<T>(title, CSVUtils.loadCSV(path, type));
    }

    static ReferenceDataset<IrisData> irisReferenceDataset(String title) {
        return createReferenceDataset(title, DataType.IRIS.getCsvPath(), DataType.IRIS);
    }

    static ReferenceDataset<TitanicPassengerData> titanicPassengerReferenceDataset(String title) {
        return createReferenceDataset(title, DataType.PASSENGER.getCsvPath(), DataType.PASSENGER);
    }

    static <T extends Data> ReferenceDataset<T> createReferenceDataset(String title, DataType type) {
        return createReferenceDataset(title, type.getCsvPath(), type);
    }
}
