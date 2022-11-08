package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import app.graphics.models.datas.data.DataType;

public abstract class CSVUtils {
	@SuppressWarnings("unchecked")
	public static <T> List<T> loadCSV(String path, DataType dataType) {
		try {
			return new CsvToBeanBuilder<T>(Files.newBufferedReader(ProjectUtils.getFilePath(path)))
					.withSeparator(',')
					.withType((Class<T>) dataType.getTypeClass())
					.build()
					.parse();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<T>();
	}
}
