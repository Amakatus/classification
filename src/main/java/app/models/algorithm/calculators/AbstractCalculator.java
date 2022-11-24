package app.models.algorithm.calculators;

import app.models.algorithm.geometry.IGeometry;
import app.models.datas.ReferenceDataset;
import app.models.datas.data.AbstractData;

public abstract class AbstractCalculator<T extends AbstractData> implements ICalculator<T> {
    protected ReferenceDataset<T> referenceDataset;
    protected IGeometry<T> geometry;

    protected AbstractCalculator(ReferenceDataset<T> algorithm, IGeometry<T> geometry) {
        this.referenceDataset = algorithm;
        this.geometry = geometry;
    }

    public ReferenceDataset<T> getReferenceDataset() {
        return this.referenceDataset;
    }

    public IGeometry<T> getGeometry() {
        return this.geometry;
    }
}
