package app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import app.graphics.models.datas.data.IrisData;

public abstract class CSVUtils {
	public static List<IrisData> loadIrisCSV() {
		try {
			return new CsvToBeanBuilder<IrisData>(Files.newBufferedReader(ProjectUtils.getFilePath("/data/iris.csv")))
					.withSeparator(',')
					.withType(IrisData.class)
					.build()
					.parse();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<IrisData>();
	}
}
