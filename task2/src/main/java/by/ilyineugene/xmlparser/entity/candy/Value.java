package by.ilyineugene.xmlparser.entity.candy;

public class Value {
    private int proteinsWeight;
    private int fatsWeight;
    private int carbohydratesWeight;

    public Value() {
    }

    public Value(int proteinsWeight, int fatsWeight, int carbohydratesWeight) {
        this.proteinsWeight = proteinsWeight;
        this.fatsWeight = fatsWeight;
        this.carbohydratesWeight = carbohydratesWeight;
    }

    public int getProteinsWeight() {
        return proteinsWeight;
    }

    public void setProteinsWeight(int proteinsWeight) {
        this.proteinsWeight = proteinsWeight;
    }

    public int getFatsWeight() {
        return fatsWeight;
    }

    public void setFatsWeight(int fatsWeight) {
        this.fatsWeight = fatsWeight;
    }

    public int getCarbohydratesWeight() {
        return carbohydratesWeight;
    }

    public void setCarbohydratesWeight(int carbohydratesWeight) {
        this.carbohydratesWeight = carbohydratesWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value value = (Value) o;
        return proteinsWeight == value.proteinsWeight && fatsWeight == value.fatsWeight && carbohydratesWeight == value.carbohydratesWeight;
    }

    @Override
    public int hashCode() {
        return 7 * proteinsWeight + 13 * fatsWeight + 31 * carbohydratesWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append(" Value{ proteinsWeight=").append(proteinsWeight);
        sb.append(", fatsWeight=").append(fatsWeight);
        sb.append(", carbohydratesWeight=").append(carbohydratesWeight).append('}');
        return sb.toString();
    }
}
