package tps.testsomething.datas;

import java.lang.reflect.Field;

public class UseData {
	public static void main(String[] args) {
		PokemonData pokemon = new PokemonData("Pikachu", 200, 50, 500, false);
		IrisData iris = new IrisData("Iris bleue", 15, "bleue");
		String output = "field %s of type %s. Is calculable for distance ? (%s)";

		System.out.println("Pokemon");
		for (Field field : pokemon.getFields()) {
			System.out.println(String.format(output, field.getName(),
					field.getType().getSimpleName(), pokemon.getAvailableDistanceField().contains(field)));
		}

		System.out.println("\n\nIris:");
		for (Field field : iris.getFields()) {
			System.out.println(String.format(output, field.getName(),
					field.getType().getSimpleName(), iris.getAvailableDistanceField().contains(field)));
		}
	}
}
