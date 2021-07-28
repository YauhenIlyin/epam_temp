package by.ilyineugen.shape.repository.impl;

import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.repository.PlanimetricShapeRepository;
import by.ilyineugen.shape.repository.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PlanimetricShapeRepositoryImpl implements PlanimetricShapeRepository {

    private static PlanimetricShapeRepositoryImpl instance;
    private List<PlanimetricShape> listOfPlanimetricShapes;

    private PlanimetricShapeRepositoryImpl() {
        listOfPlanimetricShapes = new ArrayList<PlanimetricShape>();
    }

    public static PlanimetricShapeRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PlanimetricShapeRepositoryImpl();
        }
        return instance;
    }

    public boolean addAll(Collection<? extends PlanimetricShape> collection) {
        return listOfPlanimetricShapes.addAll(collection);
    }

    public boolean removeAll(Collection<?> collection) {
        return listOfPlanimetricShapes.removeAll(collection);
    }

    public PlanimetricShape getByIndex(int index) {
        return listOfPlanimetricShapes.get(index);
    }

    public PlanimetricShape setByIndex(int index, PlanimetricShape planimetricShape) {
        return listOfPlanimetricShapes.set(index, planimetricShape);
    }

    public List<PlanimetricShape> queryWithFilterBySpecification(Specification specification) {
        List<PlanimetricShape> list = listOfPlanimetricShapes.stream().filter(shapeContainer -> specification.specify(shapeContainer)).collect(Collectors.toList());
        return list;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        return "PlanimetricShapeRepositoryImpl{" +
                "listOfPlanimetricShapes=" + listOfPlanimetricShapes +
                '}';
    }
}
