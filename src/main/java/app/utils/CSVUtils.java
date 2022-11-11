package app.utils;

import app.graphics.models.datas.data.DataType;
import com.opencsv.bean.CsvToBeanBuilder;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class CSVUtils {
	@SuppressWarnings("unchecked")
	public static <T> List<T> loadCSV(String path, DataType dataType) {
		try {
			return new CsvToBeanBuilder<T>(Files.newBufferedReader(ProjectUtils.getFilePath(path)))
					.withSeparator(',')
					.withType((Class<T>) dataType.getTypeClass())
					.build()
					.parse();
		} catch (Exception e) {
			Logger.exception(e.getMessage());
		}
		// Should throw an exception ?
		return new ArrayList<T>();
	}
}
