package tps.tpcsv.tppokemon.distance;

import tps.tpcsv.tppokemon.Pokemon;

public class DistanceEuclidienne implements Distance {

	@Override
	public double distance(Pokemon p1, Pokemon p2) {
		return 
				Math.sqrt(Math.pow((p1.getBaseEggSteps()-p2.getBaseEggSteps()),2)
				+ Math.pow((p1.getCaptureRate()-p2.getCaptureRate()),2)
				+ Math.pow((p1.getExperienceGrowth()-p2.getExperienceGrowth()),2)
				+ Math.pow((p1.getSpeed()-p2.getSpeed()),2));
	}
}
