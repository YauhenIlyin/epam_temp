package by.ilyineugen.shape.observer;

import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.observer.impl.QuadrangleObserver;

public interface Observable {

    void attach(QuadrangleObserver observer);

    void detach(QuadrangleObserver observer);

    void notifyObservers() throws ShapeException;

}
