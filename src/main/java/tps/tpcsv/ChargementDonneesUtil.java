package tps.tpcsv;

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
	
	public static Personne genereatePersonneFromData(FormatDonneeBrut data, List<FormatDonneeBrut> datas) {
		return new Personne(data, ChargementDonneesUtil.normalizeScore(data.getScore(), datas));
	}
	
	public static double[] getMinMaxScore(List<FormatDonneeBrut> datas) {
		List<FormatDonneeBrut> sortedDatas = datas.stream()
				.sorted(Comparator.comparingDouble(FormatDonneeBrut::getScore))
				.collect(Collectors.toList());
		
		double min = sortedDatas.get(0).getScore();
		double max = sortedDatas.get(datas.size()-1).getScore();
		
		return new double[] {min, max};
	}
	
	public static double roundToTwoDecimal(double toRound) {
		return (double) Math.round(toRound * 100) / 100;
	}
	
	public static double normalizeScore(double score, List<FormatDonneeBrut> datas) {
		double[] minMax = ChargementDonneesUtil.getMinMaxScore(datas);
		double min = minMax[0];
		double max = minMax[1];
		return ChargementDonneesUtil.roundToTwoDecimal((score-min)/(max-min));
	}
}

