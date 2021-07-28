package by.ilyineugen.shape.repository.impl;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.repository.Specification;
import by.ilyineugen.shape.service.PlanimetricQuadrangleOperation;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

public class QuadranglePerimeterSpecification implements Specification {
    private double leftRangeBorder;
    private double rightRangeBorder;

    public QuadranglePerimeterSpecification(double leftRangeBorder, double rightRangeBorder) {
        this.leftRangeBorder = leftRangeBorder;
        this.rightRangeBorder = rightRangeBorder;
    }


    @Override
    public boolean specify(PlanimetricShape planimetricShape) {
        if (planimetricShape == null || planimetricShape.getClass() != EntityQuadrangle.class
                || !RegularQuadrangleValidator.isValid((EntityQuadrangle) planimetricShape)) {
            return false;
        }
        EntityQuadrangle entityQuadrangle = (EntityQuadrangle) planimetricShape;
        double entityQuadranglePerimeter = 0.;
        try {
            entityQuadranglePerimeter = PlanimetricQuadrangleOperation.getPerimeter(entityQuadrangle);
        } catch (ShapeException e) {
            e.printStackTrace();
        }
        return entityQuadranglePerimeter >= leftRangeBorder && entityQuadranglePerimeter <= rightRangeBorder;
    }

    public double getLeftRangeBorder() {
        return leftRangeBorder;
    }

    public void setLeftRangeBorder(double leftRangeBorder) {
        this.leftRangeBorder = leftRangeBorder;
    }

    public double getRightRangeBorder() {
        return rightRangeBorder;
    }

    public void setRightRangeBorder(double rightRangeBorder) {
        this.rightRangeBorder = rightRangeBorder;
    }
}
