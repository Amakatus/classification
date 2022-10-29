package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import app.graphics.models.datas.data.IrisData;

public abstract class CSVUtils {
	public static List<IrisData> loadIrisCSV() throws IOException {
		return new CsvToBeanBuilder<IrisData>(Files.newBufferedReader(ProjectUtils.getFilePath("/data/iris.csv")))
				.withSeparator(',')
				.withType(IrisData.class)
				.build()
				.parse();
	}
}
