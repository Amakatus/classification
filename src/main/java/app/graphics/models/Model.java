package app.graphics.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Model implements Observable {
    protected List<Observer> observers;

    public Model() {
        this.observers = new ArrayList<>();
    }
}
