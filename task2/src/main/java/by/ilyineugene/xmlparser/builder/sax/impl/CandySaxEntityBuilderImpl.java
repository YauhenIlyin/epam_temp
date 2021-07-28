package by.ilyineugene.xmlparser.builder.sax.impl;

import by.ilyineugene.xmlparser.builder.EntityBuilder;
import by.ilyineugene.xmlparser.builder.sax.SaxEntityBuilder;
import by.ilyineugene.xmlparser.entity.candy.CandyEntity;
import by.ilyineugene.xmlparser.entity.candy.Ingredient;
import by.ilyineugene.xmlparser.entity.candy.Type;
import by.ilyineugene.xmlparser.entity.candy.Value;
import by.ilyineugene.xmlparser.evidence.candy.CandyType;
import by.ilyineugene.xmlparser.evidence.candy.Filling;
import by.ilyineugene.xmlparser.evidence.candy.IngredientType;
import by.ilyineugene.xmlparser.evidence.candy.XmlCandyTag;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;

import org.xml.sax.Attributes;

public class CandySaxEntityBuilderImpl extends EntityBuilder implements SaxEntityBuilder {

    private XmlCandyTag listEntityTag = XmlCandyTag.CANDIES;
    private XmlCandyTag entityTag = XmlCandyTag.CANDY_ENTITY;
    private static EnumSet<XmlCandyTag> enumSetWithText;
    private HashSet<CandyEntity> candyHashSet;
    private CandyEntity currentEntity;
    private XmlCandyTag currentXmlTag;
    private Ingredient currentIngredient;
    private Value currentValue;
    private ArrayList<Ingredient> currentIngredientList;
    private boolean buildCompleted = false;

    public CandySaxEntityBuilderImpl() throws XmlParserAppException {
        enumSetWithText.range(XmlCandyTag.ID, XmlCandyTag.CREATION_DATA);
    }

    public void startBuildElement(String qName, Attributes attrs) {
        XmlCandyTag xmlCandyTag = XmlCandyTag.valueOf(qName.toUpperCase());
        switch (xmlCandyTag) {
            case CANDIES:
                this.candyHashSet = new HashSet<CandyEntity>();
                break;
            case CANDY_ENTITY:
                this.currentEntity = new CandyEntity();
                currentEntity.setId(attrs.getValue(0));
                break;
            case TYPE:
                Type type = new Type();
                currentEntity.setType(type);
                if (attrs.getLength() == 1) {
                    type.setFilling(Filling.valueOf(attrs.getValue(0)));
                } else {
                    type.setFilling(Filling.LACK);
                }
                break;
            case INGREDIENTS:
                currentIngredientList = new ArrayList<Ingredient>();
                break;
            case INGREDIENT:
                currentIngredient = new Ingredient();
            case VALUE:
                currentValue = new Value();
                break;
            default:
                currentXmlTag = xmlCandyTag;
        }
    }

    public void buildElementValue(String data) {
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case ID:
                    currentEntity.setId(data);
                    break;
                case NAME:
                    currentEntity.setName(data);
                    break;
                case ENERGY:
                    currentEntity.setEnergy(Integer.parseInt(data));
                    break;
                case CANDY_TYPE:
                    CandyType candyType = CandyType.valueOf(data);
                    currentEntity.getType().setCandyType(candyType);
                    break;
                case INGREDIENT_TYPE:
                    IngredientType ingredientType = IngredientType.valueOf(data);
                    currentIngredient.setIngredientType(ingredientType);
                    break;
                case INGREDIENT_WEIGHT:
                    currentIngredient.setIngredientWeight(Integer.parseInt(data));
                    break;
                case PROTEINS_WEIGHT:
                    currentValue.setProteinsWeight(Integer.parseInt(data));
                    break;
                case FATS_WEIGHT:
                    currentValue.setFatsWeight(Integer.parseInt(data));
                    break;
                case CARBOHYDRATES_WEIGHT:
                    currentValue.setCarbohydratesWeight(Integer.parseInt(data));
                    break;
                case PRODUCTION:
                    currentEntity.setProduction(data);
                    break;
                case CREATION_DATA:
                    int year = Integer.parseInt(data.substring(0, 4));
                    int month = Integer.parseInt(data.substring(5, 7));
                    int day = Integer.parseInt(data.substring(8, 10));
                    LocalDate date = LocalDate.of(year, month, day);
                    currentEntity.setCreationDate(date);
                    break;
            }
        }
    }

    public void endBuildElement(String qName) {
        XmlCandyTag xmlCandyTag = XmlCandyTag.valueOf(qName.toUpperCase());
        currentXmlTag = null;
        if (XmlCandyTag.CANDIES.equals(xmlCandyTag)) {
            buildCompleted = true;
        } else if (XmlCandyTag.CANDY_ENTITY.equals(xmlCandyTag)) {
            this.candyHashSet.add(currentEntity);
        } else if (XmlCandyTag.INGREDIENT.equals(xmlCandyTag)) {
            currentIngredientList.add(currentIngredient);
        } else if (XmlCandyTag.INGREDIENTS.equals(xmlCandyTag)) {
            currentEntity.setIngredients(currentIngredientList);
        } else if (XmlCandyTag.VALUE.equals(xmlCandyTag)) {
            currentEntity.setValue(currentValue);
        }
    }

    public HashSet getEntityHashSet() throws XmlParserAppException {
        if (buildCompleted == false) {
            throw new XmlParserAppException("SaxCandyEntityBuilder.class getEntityHashSet(): build is not complete. error..");
        }
        return this.candyHashSet;
    }

}
