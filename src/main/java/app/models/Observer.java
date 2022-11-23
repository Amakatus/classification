package app.models;

public interface Observer {
    void sendUpdate();

    void sendUpdate(Object object);
}
