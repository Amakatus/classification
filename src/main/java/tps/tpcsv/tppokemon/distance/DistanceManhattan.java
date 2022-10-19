package tps.tpcsv.tppokemon.distance;

import tps.tpcsv.tppokemon.Pokemon;

public class DistanceManhattan implements Distance {

	@Override
	public double distance(Pokemon p1, Pokemon p2) {
		return 
				Math.abs(p1.getBaseEggSteps()-p2.getBaseEggSteps())
				+ Math.abs(p1.getCaptureRate()-p2.getCaptureRate())
				+ Math.abs(p1.getExperienceGrowth()-p2.getExperienceGrowth())
				+ Math.abs(p1.getSpeed()-p2.getSpeed());
	}
}
