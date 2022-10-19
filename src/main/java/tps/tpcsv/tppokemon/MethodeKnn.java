package tps.tpcsv.tppokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tps.tpcsv.tppokemon.distance.Distance;
import tps.tpcsv.tppokemon.distance.DistanceManhattanNormalize;

public class MethodeKnn {
	public static List<Pokemon> datas;
	public static double deltaBaseEggSteps;
	public static double deltaCaptureRate;
	public static double deltaExperienceGrowth;
	public static double deltaSpeed;
	
	public MethodeKnn(String pathToPokemonCsv) {
		try {
			MethodeKnn.datas = CSVLoaderUtil.charger(pathToPokemonCsv);
			MethodeKnn.setupDeltas();
		} catch (IOException e) { e.printStackTrace(); }
	}

	private static void setupDeltas() {
		MethodeKnn.setupDeltaSpeed();
		MethodeKnn.setupDeltaCaptureRate();
		MethodeKnn.setupDeltaExperienceGrowth();
		MethodeKnn.setupDeltaBaseEggSteps();
	}

	private static void setupDeltaBaseEggSteps() {
		List<Pokemon> sortedDatas = datas.stream()
				.sorted(Comparator.comparingDouble(Pokemon::getBaseEggSteps))
				.collect(Collectors.toList());
		double min = sortedDatas.get(0).getBaseEggSteps();
		double max = sortedDatas.get(datas.size()-1).getBaseEggSteps();
		deltaBaseEggSteps = max - min;
	}

	private static void setupDeltaExperienceGrowth() {
		List<Pokemon> sortedDatas = datas.stream()
				.sorted(Comparator.comparingDouble(Pokemon::getExperienceGrowth))
				.collect(Collectors.toList());
		double min = sortedDatas.get(0).getExperienceGrowth();
		double max = sortedDatas.get(datas.size()-1).getExperienceGrowth();
		deltaExperienceGrowth = max - min;
	}

	private static void setupDeltaCaptureRate() {
		List<Pokemon> sortedDatas = datas.stream()
				.sorted(Comparator.comparingDouble(Pokemon::getCaptureRate))
				.collect(Collectors.toList());
		double min = sortedDatas.get(0).getCaptureRate();
		double max = sortedDatas.get(datas.size()-1).getCaptureRate();
		deltaCaptureRate = max - min;
	}

	private static void setupDeltaSpeed() {
		List<Pokemon> sortedDatas = datas.stream()
				.sorted(Comparator.comparingDouble(Pokemon::getSpeed))
				.collect(Collectors.toList());
		double min = sortedDatas.get(0).getSpeed();
		double max = sortedDatas.get(datas.size()-1).getSpeed();
		deltaSpeed = max - min;
	}
	
	public static Pokemon[] knn(int k, Pokemon pokemon, Distance distance){
		HashMap<Pokemon, Double> map = new HashMap<>();
		Pokemon[] res = new Pokemon[k];
		for(Pokemon _pokemon : MethodeKnn.datas) {
			map.put(_pokemon, distance.distance(pokemon, _pokemon));
		}
		List<Entry<Pokemon, Double>> topK =
			    map.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue())
			       .limit(k+1)
			       .collect(Collectors.toList());
		for(int i = 1; i <= k; i++) {
			res[i-1] = topK.get(i).getKey();
		}
		return res;
	}
	
	public static boolean isLegendaryKnn(int k, Pokemon pokemon, Distance distance) {
		int isLegendary = 0;
		Pokemon[] knn = knn(k, pokemon, distance);
		for(Pokemon _pokemon : knn) {
			if(_pokemon.isLegendary()) {
				isLegendary++;
			} else {
				isLegendary--;
			}
		}
		return isLegendary > 0;
	}
	
	public static void main(String[] args) {
		MethodeKnn knn = new MethodeKnn("/data/pokemon_train.csv");
		System.out.println(MethodeKnn.datas);
		System.out.println(MethodeKnn.deltaCaptureRate);
		System.out.println(MethodeKnn.deltaBaseEggSteps);
		System.out.println(MethodeKnn.deltaExperienceGrowth);
		System.out.println(MethodeKnn.deltaSpeed);
		
		Pokemon findKNN = datas.get(0);
		int k = 3;
		Distance distance = new DistanceManhattanNormalize();
		Pokemon[] pokemons = MethodeKnn.knn(k, findKNN, distance);
		System.out.println(String.format("%sNN of %s are:", k, findKNN.getName()));
		System.out.println(findKNN);
		for(Pokemon pokemon: pokemons) {
			System.out.println(pokemon);
		}
		System.out.println(String.format(
				"Is %s legendary ? Probably %s", findKNN.getName(), isLegendaryKnn(k, findKNN, distance)
		));
	}
}
