package by.ilyineugen.shape.repository;

import by.ilyineugen.shape.entity.PlanimetricShape;

public interface Specification <T extends PlanimetricShape> {

    public boolean specify(T t);

}
