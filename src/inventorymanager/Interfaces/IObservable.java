package inventorymanager.Interfaces;

public interface IObservable {
    void attach(IObserver observer);
    void detatch(IObserver observer);
    void notifyObservers();
}