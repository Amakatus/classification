package app.datas.columns;

import app.algorithm.normalizers.IColumnNormalizer;
import app.datas.points.IPoint;
import app.graphics.models.DataSet;

public interface IColumn {
	public void setNormalizer(IColumnNormalizer valueNormalizer);
	public double getNormalizedValue(IPoint point);
	public Object getDenormalizedValue(double value);
	public String getName();
	public DataSet<?> getDataset();
	public boolean isNormalizable();
}
