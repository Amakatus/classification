package app.graphics.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Model implements Observable {
    protected List<Observer> observers;

    public Model() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateObservers(Object object) {
        this.observers.forEach(observer -> observer.sendUpdate(this));
    }
}
