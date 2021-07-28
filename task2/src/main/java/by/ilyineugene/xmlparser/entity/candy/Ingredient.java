package by.ilyineugene.xmlparser.entity.candy;

import by.ilyineugene.xmlparser.evidence.candy.IngredientType;

public class Ingredient {
    private IngredientType ingredientType;
    private int ingredientWeight;

    public Ingredient() {
    }

    public Ingredient(IngredientType ingredientType, int ingredientWeight) {
        this.ingredientType = ingredientType;
        this.ingredientWeight = ingredientWeight;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public int getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(int ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return ingredientWeight == that.ingredientWeight && ingredientType == that.ingredientType;
    }

    @Override
    public int hashCode() {
        return ingredientType.hashCode() + 31 * ingredientWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(65);
        sb.append("Ingredient{ ingredientType=").append(ingredientType);
        sb.append(", ingredientWeight=").append(ingredientWeight).append('}');
        return sb.toString();
    }
}
