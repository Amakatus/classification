package app.graphics.models;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements Observable {
    protected List<Observer> observers;

    public AbstractModel() {
        this.observers = new ArrayList<>();
    }
}
