package main.java.tps.tpcsv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class ChargementDonneesUtil {
	public static List<FormatDonneeBrut> charger(String fileName) throws IOException {
		Path path = new File(ChargementDonneesUtil.class
				.getResource(fileName)
				.getFile()).toPath();
		return new CsvToBeanBuilder<FormatDonneeBrut>(Files.newBufferedReader(path))
				.withSeparator(';')
				.withType(FormatDonneeBrut.class)
				.build()
				.parse();
	}
}
