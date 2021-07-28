package by.ilyineugen.shape.repository;

public class PlanimetricShapeWarehouseParameter {
    private long shapeId;
    private double perimeter;
    private double area;

    public PlanimetricShapeWarehouseParameter(long shapeId, double perimeter, double area) {
        this.shapeId = shapeId;
        this.perimeter = perimeter;
        this.area = area;

    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public long getShapeId() {
        return shapeId;
    }

    public void setShapeId(long shapeId) {
        this.shapeId = shapeId;
    }
}
