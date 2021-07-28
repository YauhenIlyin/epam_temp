package by.ilyineugen.shape.validator;

import by.ilyineugen.shape.entity.PlanimetricPoint;
import by.ilyineugen.shape.entity.EntityQuadrangle;

public class RegularQuadrangleValidator {

    private static final int COUNT_OF_COORDINATES_IN_QUADRANGLE = 8;
    private static final int COUNT_OF_PLANIMETRIC_POINTS_IN_QUADRANGLE = 4;

    public static boolean isValid(EntityQuadrangle entityQuadrangle) {
        return entityQuadrangle != null && isValid(entityQuadrangle.getPointArray());
    }

    public static boolean isValid(PlanimetricPoint[] pointsArr) {
        return pointsArr != null && pointsArr.length == COUNT_OF_PLANIMETRIC_POINTS_IN_QUADRANGLE &&
                isValid(pointsArr[0], pointsArr[1], pointsArr[2], pointsArr[3]);
    }

    public static boolean isValid(PlanimetricPoint a, PlanimetricPoint b, PlanimetricPoint c, PlanimetricPoint d) {
        return (a != null && b != null && c != null && d != null) &&
                isValid(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY());
    }

    public static boolean isValid(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        return isValidCalculate(x0, y0, x1, y1, x2, y2, x3, y3);
    }

    public static boolean isValid(double[] arrayOfCoordinates) {
        return arrayOfCoordinates.length == COUNT_OF_COORDINATES_IN_QUADRANGLE && isValidCalculate(arrayOfCoordinates);
    }

    private static boolean isValidCalculate(double... primitiveCoordinateArr) {
        double xOfFirstDiagonalCenter = (primitiveCoordinateArr[0] + primitiveCoordinateArr[4]) / 2;
        xOfFirstDiagonalCenter = Math.round(xOfFirstDiagonalCenter * 10) / 10;
        double yOfFirstDiagonalCenter = (primitiveCoordinateArr[1] + primitiveCoordinateArr[5]) / 2;
        yOfFirstDiagonalCenter = Math.round(yOfFirstDiagonalCenter * 10) / 10;
        double xOfSecondDiagonalCenter = (primitiveCoordinateArr[2] + primitiveCoordinateArr[6]) / 2;
        xOfSecondDiagonalCenter = Math.round(xOfSecondDiagonalCenter * 10) / 10;
        double yOfSecondDiagonalCenter = (primitiveCoordinateArr[3] + primitiveCoordinateArr[7]) / 2;
        yOfSecondDiagonalCenter = Math.round(yOfSecondDiagonalCenter * 10) / 10;
        return xOfFirstDiagonalCenter == xOfSecondDiagonalCenter && yOfFirstDiagonalCenter == yOfSecondDiagonalCenter;
    }
}
