package app.algorithm.normalizers;

public interface IColumnNormalizer {
	double normalize(Object value);
	Object denormalize(double value);
}
