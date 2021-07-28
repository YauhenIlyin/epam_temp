package by.ilyineugen.shape.repository.impl;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.repository.PlanimetricShapeWarehouse;
import by.ilyineugen.shape.repository.PlanimetricShapeWarehouseParameter;
import by.ilyineugen.shape.service.PlanimetricQuadrangleOperation;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

import java.util.HashMap;
import java.util.Map;

public class PlanimetricShapeWarehouseImpl implements PlanimetricShapeWarehouse {

    private static PlanimetricShapeWarehouseImpl instance;
    private Map<Long, PlanimetricShapeWarehouseParameter> shapeParameterMap;

    private PlanimetricShapeWarehouseImpl() {
        shapeParameterMap = new HashMap<Long, PlanimetricShapeWarehouseParameter>();
    }

    public static PlanimetricShapeWarehouseImpl getInstance() {
        if (instance == null) {
            instance = new PlanimetricShapeWarehouseImpl();
        }
        return instance;
    }

    public void addShapeParameter(EntityQuadrangle entityQuadrangle) throws ShapeException {
        if (!RegularQuadrangleValidator.isValid(entityQuadrangle)) {
            throw new ShapeException("321312");
        }
        double area = PlanimetricQuadrangleOperation.getArea(entityQuadrangle);
        double perimeter = PlanimetricQuadrangleOperation.getPerimeter(entityQuadrangle);
        long shapeId = entityQuadrangle.getId();
        PlanimetricShapeWarehouseParameter parameter = new PlanimetricShapeWarehouseParameter(shapeId, perimeter, area);
        shapeParameterMap.put(parameter.getShapeId(), parameter);
    }

    public boolean addShapeParameter(long shapeId, double perimeter, double area) throws ShapeException {
        if (shapeParameterMap.containsKey(shapeId)) {
            return false;
        }
        PlanimetricShapeWarehouseParameter parameter = new PlanimetricShapeWarehouseParameter(shapeId, perimeter, area);
        shapeParameterMap.put(shapeId, parameter);
        return true;
    }

    public PlanimetricShapeWarehouseParameter getShapeParametersById(long shapeId) throws ShapeException {
        if (!shapeParameterMap.containsKey(shapeId)) {
            throw new ShapeException("dsad");
        }
        return shapeParameterMap.get(shapeId);
    }

    public double getPerimeterById(long shapeId) throws ShapeException {
        if (!shapeParameterMap.containsKey(shapeId)) {
            throw new ShapeException("fds");
        }
        return shapeParameterMap.get(shapeId).getPerimeter();
    }

    public double getAreaById(long shapeId) throws ShapeException {
        if (!shapeParameterMap.containsKey(shapeId)) {
            throw new ShapeException("fds");
        }
        return shapeParameterMap.get(shapeId).getArea();
    }

    public boolean setShapeParameterById(long shapeId, PlanimetricShapeWarehouseParameter parameter) throws ShapeException {
        if (parameter == null) {
            throw new ShapeException("fsd");
        }
        if (shapeParameterMap.containsKey(shapeId)) {
            PlanimetricShapeWarehouseParameter container = shapeParameterMap.get(shapeId);
            container.setPerimeter(parameter.getPerimeter());
            container.setArea(parameter.getArea());
            return true;
        } else {
            return false;
        }
    }

    public boolean setShapeParameterById(long shapeId, double perimeter, double area) {
        if (shapeParameterMap.containsKey(shapeId)) {
            PlanimetricShapeWarehouseParameter container = shapeParameterMap.get(shapeId);
            container.setPerimeter(perimeter);
            container.setArea(area);
            return true;
        } else {
            return false;
        }
    }

    public boolean isContainsParameterOfShapeById(long shapeId) {
        return shapeParameterMap.containsKey(shapeId);
    }

}
