package controller.interfaces;

public interface IShapeListSubject {
	void registerObserver(IShapeListObserver observer);
	void removeObserver(IShapeListObserver observer);
	void notifyObservers();
}