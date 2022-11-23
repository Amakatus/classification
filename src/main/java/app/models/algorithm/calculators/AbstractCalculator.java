package app.models.algorithm.calculators;

import app.models.algorithm.Algorithm;
import app.models.algorithm.geometry.IGeometryCalculator;
import app.models.datas.ReferenceDataset;
import app.models.datas.WorkingDataset;
import app.models.datas.data.AbstractData;

import java.util.List;

public abstract class AbstractCalculator<T extends AbstractData> implements ICalculator<T> {
    protected Algorithm<T> algorithm;
    protected IGeometryCalculator<T> geometry;

    protected AbstractCalculator(Algorithm<T> algorithm, IGeometryCalculator<T> geometry) {
        this.algorithm = algorithm;
        this.geometry = geometry;
    }

    public ReferenceDataset<T> getReferenceDataset() {
        return this.algorithm.getReferenceDataset();
    }

    public IGeometryCalculator<T> getGeometry() {
        return this.geometry;
    }
}
