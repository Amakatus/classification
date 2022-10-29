package app.graphics.models.datas.columns;

import app.algorithm.normalizers.IColumnNormalizer;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.points.IPoint;

public interface IColumn {
	public void setNormalizer(IColumnNormalizer valueNormalizer);
	public double getNormalizedValue(IPoint point);
	public Object getDenormalizedValue(double value);
	public String getName();
	public Dataset<?> getDataset();
	public boolean isNormalizable();
}
