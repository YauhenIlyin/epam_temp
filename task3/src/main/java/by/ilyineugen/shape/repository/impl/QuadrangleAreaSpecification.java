package by.ilyineugen.shape.repository.impl;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.repository.Specification;
import by.ilyineugen.shape.service.PlanimetricQuadrangleOperation;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

public class QuadrangleAreaSpecification implements Specification {

    private double leftRangeBorder;
    private double rightRangeBorder;

    public QuadrangleAreaSpecification(double leftRangeBorder, double rightRangeBorder) {
        this.leftRangeBorder = leftRangeBorder;
        this.rightRangeBorder = rightRangeBorder;
    }

    @Override
    public boolean specify(PlanimetricShape planimetricShape) {
        if (planimetricShape == null || planimetricShape.getClass() != EntityQuadrangle.class || !RegularQuadrangleValidator.isValid((EntityQuadrangle) planimetricShape)) {
            return false;
        }
        EntityQuadrangle entityQuadrangle = (EntityQuadrangle) planimetricShape;
        double entityQuadrangleArea = 0.;
        try {
            entityQuadrangleArea = PlanimetricQuadrangleOperation.getArea(entityQuadrangle);
        } catch (ShapeException e) {
            e.printStackTrace();
        }
        return entityQuadrangleArea >= leftRangeBorder && entityQuadrangleArea <= rightRangeBorder;
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
