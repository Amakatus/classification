package app.models.algorithm.geometry;

public interface GeneralToDouble {
    static double toDouble(Object working, Object ref) {
        return working.toString().equalsIgnoreCase(ref.toString()) ? 0 : 1;
    }
}
