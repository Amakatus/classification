package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public abstract class CSVUtils {
	public static <T> List<T> loadCSV(String path, Class<T> tClass) {
		try {
			return new CsvToBeanBuilder<T>(Files.newBufferedReader(ProjectUtils.getFilePath(path)))
					.withSeparator(',')
					.withType(tClass)
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
