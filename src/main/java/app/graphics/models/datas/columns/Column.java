package app.graphics.models.datas.columns;

import app.algorithm.normalizers.IColumnNormalizer;
import app.graphics.models.datas.Dataset;
import app.graphics.models.datas.data.Data;
import app.graphics.models.datas.points.IPoint;

public class Column<T extends Data> implements IColumn {
	protected IColumnNormalizer normalizer;
	protected String name;
	protected Dataset<T> dataSet;
	
	@Override
	public void setNormalizer(IColumnNormalizer valueNormalizer) {
		// TODO Auto-generated method stub
	}

	@Override
	public double getNormalizedValue(IPoint point) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getDenormalizedValue(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dataset<T> getDataset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNormalizable() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
