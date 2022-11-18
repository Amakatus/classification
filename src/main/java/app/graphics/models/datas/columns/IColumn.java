package app.graphics.models.datas.columns;

import app.algorithm.normalizers.IColumnNormalizer;
import app.graphics.models.datas.AbstractDataset;
import app.graphics.models.datas.points.IPoint;

public interface IColumn {
	void setNormalizer(IColumnNormalizer valueNormalizer);
	double getNormalizedValue(IPoint point);
	Object getDenormalizedValue(double value);
	String getName();
	AbstractDataset<?> getDataset();
	boolean isNormalizable();
}
