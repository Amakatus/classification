package app.graphics.models.datas.columns;

import app.algorithm.normalizers.IColumnNormalizer;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.points.IPoint;

public interface IColumn {
	void setNormalizer(IColumnNormalizer valueNormalizer);
	double getNormalizedValue(IPoint point);
	Object getDenormalizedValue(double value);
	String getName();
	Dataset<?> getDataset();
	boolean isNormalizable();
}
