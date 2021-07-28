package by.ilyineugen.shape.entity;

public class PlanimetricPoint {
    private double coordinateX;
    private double coordinateY;

    public PlanimetricPoint(double coordinateX, double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public double getX() {
        return coordinateX;
    }

    public double getY() {
        return coordinateY;
    }

    public void setX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "PlanimetricPoint: " + " " + coordinateX + " " + coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        PlanimetricPoint that = (PlanimetricPoint) o;
        return Double.compare(that.coordinateX, this.coordinateX) == 0 && Double.compare(that.coordinateY, this.coordinateY) == 0;
    }

    @Override
    public int hashCode() {
        return (int) (coordinateX * 31 + coordinateY * 5);
    }
}
