package by.ilyineugen.shape.factory;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.entity.PlanimetricPoint;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

public class QuadrangleFactory extends ShapeFactory {

    @Override
    public EntityQuadrangle createShape(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws ShapeException {
        PlanimetricPoint[] pointArray;
        if (!RegularQuadrangleValidator.isValid(x1, y1, x2, y2, x3, y3, x4, y4)) {
            throw new ShapeException("2222");
        }
        return calculateShape(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    @Override
    public EntityQuadrangle createShape(double[] coordinateArr) throws ShapeException {
        if (!RegularQuadrangleValidator.isValid(coordinateArr)) {
            throw new ShapeException("sss");
        }
        return calculateShape(coordinateArr);
    }

    @Override
    public EntityQuadrangle createShape(PlanimetricPoint[] pointArr) throws ShapeException {
        if (!RegularQuadrangleValidator.isValid(pointArr)) {
            throw new ShapeException("222");
        }
        return new EntityQuadrangle(pointArr);
    }

    private EntityQuadrangle calculateShape(double... coordinateArr) {
        PlanimetricPoint[] pointArray;
        pointArray = new PlanimetricPoint[4];
        for (int planimetricPointIndex = 0, xIndex = 0, yIndex = 1; planimetricPointIndex < pointArray.length; planimetricPointIndex++, xIndex += 2, yIndex += 2) {
            pointArray[planimetricPointIndex] = new PlanimetricPoint(coordinateArr[xIndex], coordinateArr[yIndex]);
        }
        return new EntityQuadrangle(pointArray);
    }
}
