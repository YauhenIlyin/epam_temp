package by.ilyineugen.shape.observer;

import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.observer.impl.QuadrangleEvent;

public interface Observer {

    void parameterChanged(QuadrangleEvent event) throws ShapeException;

}
