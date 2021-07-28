package by.ilyineugen.shape.entity;

public abstract class PlanimetricShape {
    private long id;

    public abstract PlanimetricPoint[] getPointArray();

    public PlanimetricShape(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
