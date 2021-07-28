package by.ilyineugene.xmlparser.entity.candy;

import by.ilyineugene.xmlparser.entity.CommonEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandyEntity extends CommonEntity {
    private String id;
    private String name;
    private int energy;
    private Type type;
    private ArrayList<Ingredient> ingredients;
    private Value value;
    private String production;
    private LocalDate creationDate;

    public CandyEntity() {
    }

    public CandyEntity(String id, String name, int energy, Type type, ArrayList<Ingredient> ingredients, Value value, String production, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.type = type;
        this.ingredients = ingredients;
        this.value = value;
        this.production = production;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CandyEntity that = (CandyEntity) o;
        return energy == that.energy && id.equals(that.id) && name.equals(that.name) &&
                type.equals(that.type) && ingredients.equals(that.ingredients) && value.equals(that.value) &&
                production.equals(that.production) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + name.hashCode() + 31 * energy + type.hashCode() +
                ingredients.hashCode() + value.hashCode() + production.hashCode() + creationDate.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(260);
        sb.append(" CandyEntity{").append(" id=").append(id).append(", name=").append(name);
        sb.append(", energy=").append(energy).append(", type=").append(type);
        sb.append(", ingredients=").append(ingredients).append(", value=").append(value);
        sb.append(", production=").append(production).append(", creationDate=").append(creationDate).append('}');
        return sb.toString();
    }
}
