package by.ilyineugene.xmlparser.evidence.candy;

public enum XmlCandyTag {

    CANDIES("candies"),
    CANDY_ENTITY("candy_entity"),
    ID("id"),
    NAME("name"),
    ENERGY("energy"),
    TYPE("type"),
    CANDY_TYPE("candy_type"),
    FILLING("filling"),
    INGREDIENTS("ingredients"),
    INGREDIENT("ingredient"),
    INGREDIENT_TYPE("ingredient_type"),
    INGREDIENT_WEIGHT("ingredient_weight"),
    VALUE("value"),
    PROTEINS_WEIGHT("proteins_weight"),
    FATS_WEIGHT("fats_weight"),
    CARBOHYDRATES_WEIGHT("carbohydrates_weight"),
    PRODUCTION("production"),
    CREATION_DATA("creation_data");

    /*
    id //attr of candy-entity
    candies
    candy-entity

    type
    candy-type
    filling //attr of "type"

    ingredients

    ingredient
    ingredient-weight
    ingredient-type

    production

    value
     proteins-weight
     fats-weight
    carbohydrates-weight
     */

    private String tagValue;

    XmlCandyTag(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getTagValue() {
        return tagValue;
    }

}
