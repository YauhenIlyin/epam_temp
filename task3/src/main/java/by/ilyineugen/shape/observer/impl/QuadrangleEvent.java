package by.ilyineugen.shape.observer.impl;

import by.ilyineugen.shape.entity.EntityQuadrangle;

import java.util.EventObject;

public class QuadrangleEvent extends EventObject {

    public QuadrangleEvent(EntityQuadrangle source) {
        super(source);
    }

    @Override
    public EntityQuadrangle getSource() {
        return (EntityQuadrangle) super.getSource();
    }

}
