package by.ilyineugen.shape.factory;

import by.ilyineugen.shape.entity.PlanimetricPoint;
import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.exception.ShapeException;

public abstract class ShapeFactory {

    public abstract PlanimetricShape createShape(double[] coordinateArr) throws ShapeException;

    public abstract PlanimetricShape createShape(PlanimetricPoint[] pointArr) throws ShapeException;

    public abstract PlanimetricShape createShape(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws ShapeException;

}
