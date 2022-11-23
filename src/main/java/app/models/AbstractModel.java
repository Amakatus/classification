package app.models;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements Observable {
    protected List<Observer> observers;

    protected AbstractModel() {
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