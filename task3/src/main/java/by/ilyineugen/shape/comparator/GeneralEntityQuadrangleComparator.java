package by.ilyineugen.shape.comparator;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.service.PlanimetricQuadrangleOperation;

import java.util.Comparator;

public class GeneralEntityQuadrangleComparator implements Comparator<EntityQuadrangle> {

    @Override
    public int compare(EntityQuadrangle o1, EntityQuadrangle o2) {
        int result = new AreaEntityQuadrangleComparator().compare(o1, o2);
        if (result == 0) {
            result = new PerimeterEntityQuadrangleComparator().compare(o1, o2);
        }
        return result;
    }
}
