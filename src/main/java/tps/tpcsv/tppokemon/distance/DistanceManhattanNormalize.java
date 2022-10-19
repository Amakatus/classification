package tps.tpcsv.tppokemon.distance;

import tps.tpcsv.tppokemon.MethodeKnn;
import tps.tpcsv.tppokemon.Pokemon;

public class DistanceManhattanNormalize implements Distance {

	@Override
	public double distance(Pokemon p1, Pokemon p2) {
		return 
				(Math.abs(p1.getBaseEggSteps()-p2.getBaseEggSteps())/MethodeKnn.deltaBaseEggSteps)
				+ (Math.abs(p1.getCaptureRate()-p2.getCaptureRate())/MethodeKnn.deltaCaptureRate)
				+ (Math.abs(p1.getExperienceGrowth()-p2.getExperienceGrowth())/MethodeKnn.deltaExperienceGrowth)
				+ (Math.abs(p1.getSpeed()-p2.getSpeed())/MethodeKnn.deltaSpeed);
	}
}
