package app.graphics.models.datas.columns;

import app.algorithm.normalizers.IDataNormalizer;
import app.graphics.models.datas.AbstractDataset;
import app.graphics.models.datas.points.IPoint;

public interface IColumn {
    void setNormalizer(IDataNormalizer valueNormalizer);

    double getNormalizedValue(IPoint point);

    Object getDenormalizedValue(double value);

    String getName();

    AbstractDataset<?> getDataset();

    boolean isNormalizable();
}
