package app.algorithm.geometry;

public abstract class GeneralToDouble {
	private  GeneralToDouble() {}

	public static double toDouble(Object working, Object ref) {
		return working.toString().equalsIgnoreCase(ref.toString()) ? 0 : 1;
	}
}
