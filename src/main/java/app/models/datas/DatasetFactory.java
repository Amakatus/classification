package app.models.datas;

import app.models.datas.data.AbstractData;
import app.models.datas.data.DataType;
import app.models.datas.data.IrisData;
import app.models.datas.data.TitanicPassengerData;
import app.utils.CSVUtils;

import java.io.File;

public interface DatasetFactory {
    static <T extends AbstractData> ReferenceDataset<T> createReferenceDataset(String title, String path, DataType type) {
        return new ReferenceDataset<>(title, CSVUtils.loadCSV(path, type));
    }

    static ReferenceDataset<IrisData> irisReferenceDataset(String title) {
        return createReferenceDataset(title, DataType.IRIS.getCsvPath(), DataType.IRIS);
    }

    static ReferenceDataset<TitanicPassengerData> titanicPassengerReferenceDataset(String title) {
        return createReferenceDataset(title, DataType.PASSENGER.getCsvPath(), DataType.PASSENGER);
    }

    static <T extends AbstractData> ReferenceDataset<T> createReferenceDataset(String title, DataType type) {
        return createReferenceDataset(title, type.getCsvPath(), type);
    }

    static <T extends AbstractData> WorkingDataset<T> createWorkingDataset(String title, DataType type, File csvFile) {
        return new WorkingDataset<>(title, CSVUtils.loadCSV(csvFile.toPath(), type), createReferenceDataset(String.format("Reference%s", title), type));
    }
}
