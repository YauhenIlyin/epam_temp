package by.ilyineugen.shape.repository;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.exception.ShapeException;

public interface PlanimetricShapeWarehouse {

    public void addShapeParameter(EntityQuadrangle entityQuadrangle) throws ShapeException;

    public boolean addShapeParameter(long shapeId, double perimeter, double area) throws ShapeException;

    public PlanimetricShapeWarehouseParameter getShapeParametersById(long shapeId) throws ShapeException;

    public double getPerimeterById(long shapeId) throws ShapeException;

    public double getAreaById(long shapeId) throws ShapeException;

    public boolean setShapeParameterById(long shapeId, PlanimetricShapeWarehouseParameter parameter) throws ShapeException;

    public boolean setShapeParameterById(long shapeId, double perimeter, double area);

    public boolean isContainsParameterOfShapeById(long shapeId);

}
