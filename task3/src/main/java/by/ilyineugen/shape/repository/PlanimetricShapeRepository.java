package by.ilyineugen.shape.repository;

import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.repository.impl.PlanimetricShapeRepositoryImpl;

import java.util.Collection;
import java.util.List;

public interface PlanimetricShapeRepository {

    public boolean addAll(Collection<? extends PlanimetricShape> collection);

    public boolean removeAll(Collection<?> collection);

    public PlanimetricShape getByIndex(int index);

    public PlanimetricShape setByIndex(int index, PlanimetricShape planimetricShape);

    public List<PlanimetricShape> queryWithFilterBySpecification(Specification specification);

}
