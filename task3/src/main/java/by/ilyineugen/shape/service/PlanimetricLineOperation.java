package by.ilyineugen.shape.service;

import by.ilyineugen.shape.entity.PlanimetricPoint;
import by.ilyineugen.shape.exception.ShapeException;

public class PlanimetricLineOperation {

    public static double lengthOfSection(PlanimetricPoint firstPoint, PlanimetricPoint secondPoint) throws ShapeException {
        if (firstPoint == null || secondPoint == null) {
            throw new ShapeException("fdsdfs");
        }
        return lengthOfSection(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
    }

    public static double lengthOfSection(double xOfFirstPoint, double yOfFirstPoint, double xOfSecondPoint, double yOfSecondPoint) {
        double length = Math.sqrt(Math.pow(xOfSecondPoint - xOfFirstPoint, 2) + Math.pow(yOfSecondPoint - yOfFirstPoint, 2));
        return Math.round(length * 10) / 10;
    }
}
