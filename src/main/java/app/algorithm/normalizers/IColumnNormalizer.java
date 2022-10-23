package app.algorithm.normalizers;

public interface IColumnNormalizer {
	public double normalize(Object value);
	public Object denormalize(double value);
}
