package by.ilyineugen.shape.service;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.exception.ShapeException;

public class PlanimetricQuadrangleOperation {

    public static double getPerimeter(EntityQuadrangle entityQuadrangle) throws ShapeException {
        double section1 = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(0), entityQuadrangle.getPointByIndex(1));
        double section2 = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(1), entityQuadrangle.getPointByIndex(2));
        return Math.round(((section1 + section2) * 2) * 10) / 10;
    }

    public static double getArea(EntityQuadrangle entityQuadrangle) throws ShapeException {
        double section1 = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(0), entityQuadrangle.getPointByIndex(1));
        double section2 = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(1), entityQuadrangle.getPointByIndex(2));
        return Math.round((section1 * section2) * 10) / 10;
    }
}
