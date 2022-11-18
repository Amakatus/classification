package app.algorithm;

import app.graphics.models.datas.data.Data;

public class KNNStrength<T extends Data> {
	protected KNNAlgorithm<T> algorithm;
	protected double strength;

	public KNNStrength(KNNAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
	}

	public KNNAlgorithm<T> getAlgorithm() {
		return this.algorithm;
	}

	public double getStrength() { return this.strength; }
}