package app.graphics.models;

public interface Observable {
    void attach(Observer observer);

    void detach(Observer observer);

    void updateObservers(Object object);
}
