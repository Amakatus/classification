package tps.tpcsv.tppokemon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

import tps.tpcsv.FormatDonneeBrut;

public class CSVLoaderUtil {
	public static List<Pokemon> charger(String fileName) throws IOException {
		Path path = new File(CSVLoaderUtil.class
				.getResource(fileName)
				.getFile()).toPath();
		return new CsvToBeanBuilder<Pokemon>(Files.newBufferedReader(path))
				.withSeparator(',')
				.withType(Pokemon.class)
				.build()
				.parse();
	}
}
