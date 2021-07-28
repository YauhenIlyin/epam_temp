package by.ilyineugene.xmlparser.entity.candy;

import by.ilyineugene.xmlparser.evidence.candy.CandyType;
import by.ilyineugene.xmlparser.evidence.candy.Filling;

public class Type {
    private CandyType candyType;
    private Filling filling;

    public Type() {
    }

    public Type(CandyType candyType, Filling filling) {
        this.candyType = candyType;
        this.filling = filling;
    }

    public Type(CandyType candyType) {
        this.candyType = candyType;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    public void setCandyType(CandyType candyType) {
        this.candyType = candyType;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Type type = (Type) o;
        return candyType == type.candyType && filling == type.filling;
    }

    @Override
    public int hashCode() {
        return candyType.hashCode() + filling.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type{ candyType=").append(candyType);
        sb.append(", filling=").append(filling).append('}');
        return sb.toString();
    }
}
