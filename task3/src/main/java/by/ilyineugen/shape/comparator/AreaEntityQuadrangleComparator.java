package by.ilyineugen.shape.comparator;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.service.PlanimetricQuadrangleOperation;

import java.util.Comparator;

public class AreaEntityQuadrangleComparator implements Comparator<EntityQuadrangle> {

    @Override
    public int compare(EntityQuadrangle o1, EntityQuadrangle o2) {
        int result = 0;
        try {
            result = (int) (PlanimetricQuadrangleOperation.getArea(o1) - PlanimetricQuadrangleOperation.getArea(o2));
        } catch (ShapeException e) {
            e.printStackTrace();
        }
        return result;
    }
}
