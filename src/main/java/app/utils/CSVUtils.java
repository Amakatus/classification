package app.utils;

import app.models.datas.data.DataType;
import com.opencsv.bean.CsvToBeanBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public interface CSVUtils {
    @SuppressWarnings("unchecked")
    static <T> List<T> loadCSV(Path path, DataType dataType) {
        try {
            return new CsvToBeanBuilder<T>(Files.newBufferedReader(path))
                    .withSeparator(',')
                    .withType((Class<T>) dataType.getTypeClass())
                    .build()
                    .parse();
        } catch (Exception e) {
            LoggerUtils.exception(e);
        }
        // Should throw an exception ?
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    static <T> List<T> loadCSV(String path, DataType dataType) {
        return loadCSV(ProjectUtils.getFilePath(path), dataType);
    }
}
