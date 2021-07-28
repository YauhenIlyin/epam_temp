package by.ilyineugen.shape.repository.impl;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.repository.Specification;
import by.ilyineugen.shape.service.PlanimetricLineOperation;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

public class QuadrangleSquareSpecification implements Specification {

    @Override
    public boolean specify(PlanimetricShape shape) {
        if (shape == null || shape.getClass() != EntityQuadrangle.class || !RegularQuadrangleValidator.isValid((EntityQuadrangle) shape)) {
            return false;
        }
        EntityQuadrangle entityQuadrangle = (EntityQuadrangle) shape;
        double firstSide = 0.;
        double secondSide = 1.;
        try {
            firstSide = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(0), entityQuadrangle.getPointByIndex(1));
            secondSide = PlanimetricLineOperation.lengthOfSection(entityQuadrangle.getPointByIndex(1), entityQuadrangle.getPointByIndex(2));
        } catch (ShapeException e) {
            e.printStackTrace();
        }
        return firstSide == secondSide;
    }
}

