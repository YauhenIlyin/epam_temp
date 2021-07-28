package by.ilyineugen.shape.entity;

import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.observer.Observable;
import by.ilyineugen.shape.observer.impl.QuadrangleEvent;
import by.ilyineugen.shape.observer.impl.QuadrangleObserver;
import by.ilyineugen.shape.repository.impl.PlanimetricShapeWarehouseImpl;
import by.ilyineugen.shape.util.IdGenerator;

import java.util.Arrays;

public class EntityQuadrangle extends PlanimetricShape implements Observable {

    private PlanimetricPoint[] pointArray;
    private QuadrangleObserver quadrangleObserver;

    public EntityQuadrangle(PlanimetricPoint pointA, PlanimetricPoint pointB, PlanimetricPoint pointC, PlanimetricPoint pointD) {
        super(IdGenerator.generateAndGetId());
        this.pointArray = new PlanimetricPoint[]{pointA, pointB, pointC, pointD};
    }

    public EntityQuadrangle(PlanimetricPoint[] pointArray) {
        super(IdGenerator.generateAndGetId());
        this.pointArray = pointArray;
    }

    public PlanimetricPoint[] getPointArray() {
        return this.pointArray;
    }

    public void setPointArray(PlanimetricPoint[] array) {
        this.pointArray = array;
    }

    public PlanimetricPoint getPointByIndex(int index) {
        return pointArray[index];
    }

    public void setPointByIndex(PlanimetricPoint point, int index) {
        this.pointArray[index] = point;
    }

    @Override
    public void attach(QuadrangleObserver observer) {
        if (observer != null) {
            this.quadrangleObserver = observer;
        }
    }

    @Override
    public void detach(QuadrangleObserver observer) {
        this.quadrangleObserver = null;
    }

    @Override
    public void notifyObservers() throws ShapeException {
        QuadrangleEvent event = new QuadrangleEvent(this);
        if (quadrangleObserver != null) {
            quadrangleObserver.parameterChanged(event);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb;
        sb = new StringBuilder(pointArray.length * 30 + 50);
        sb.append("EntityQuadrangle{");
        sb.append("coordinatesArray=");
        sb.append(Arrays.toString(pointArray));
        sb.append(", id=").append(this.getId()).append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        EntityQuadrangle that = (EntityQuadrangle) o;
        return this.getId() == that.getId() && Arrays.equals(this.pointArray, that.pointArray);
    }

    @Override
    public int hashCode() {
        return (int) (31 * getId() + Arrays.hashCode(pointArray));
    }
}
