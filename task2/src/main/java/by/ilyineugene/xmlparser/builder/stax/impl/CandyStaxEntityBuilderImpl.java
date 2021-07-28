package by.ilyineugene.xmlparser.builder.stax.impl;

import by.ilyineugene.xmlparser.builder.EntityBuilder;
import by.ilyineugene.xmlparser.builder.stax.StaxEntityBuilder;
import by.ilyineugene.xmlparser.entity.candy.CandyEntity;
import by.ilyineugene.xmlparser.entity.candy.Ingredient;
import by.ilyineugene.xmlparser.entity.candy.Type;
import by.ilyineugene.xmlparser.entity.candy.Value;
import by.ilyineugene.xmlparser.evidence.candy.CandyType;
import by.ilyineugene.xmlparser.evidence.candy.Filling;
import by.ilyineugene.xmlparser.evidence.candy.IngredientType;
import by.ilyineugene.xmlparser.evidence.candy.XmlCandyTag;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class CandyStaxEntityBuilderImpl extends EntityBuilder implements StaxEntityBuilder {

    private HashSet<CandyEntity> candyHashSet;
    private boolean buildCompleted = false;

    public void parseAndBuildEntityHashSet(XMLStreamReader reader) throws XmlParserAppException {
        try {
            String tagName;
            candyHashSet = new HashSet<>();
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    tagName = reader.getLocalName();
                    if (tagName.equals(XmlCandyTag.CANDY_ENTITY.getTagValue())) {
                        candyHashSet.add(buildEntity(reader));
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new XmlParserAppException("CandyStaxEntityBuilderImpl.class : parseAndBuildEntityHashSet(XMLStreamReader r) : XMLStreamReader methods");
        }
        buildCompleted = true;
    }

    private CandyEntity buildEntity(XMLStreamReader reader) throws XMLStreamException, XmlParserAppException {
        CandyEntity candyEntity = new CandyEntity();
        if (reader.getAttributeCount() == 1 && reader.getAttributeName(0).toString().equals(XmlCandyTag.ID.getTagValue())) {
            String candyId = reader.getAttributeValue(0);
            candyEntity.setId(candyId);
        } else {
            throw new XmlParserAppException("count of Candy_Entity attributes in xml file != 1 and not correct");
        }
        boolean entityBuildCompleted = false;
        String tagName = null;
        String strContainer;
        while (!entityBuildCompleted) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT || type == XMLStreamConstants.END_ELEMENT) {
                tagName = reader.getLocalName();
            }
            if (type == XMLStreamConstants.START_ELEMENT) {
                XmlCandyTag currentXmlCandyTag = XmlCandyTag.valueOf(tagName.toUpperCase());
                switch (currentXmlCandyTag) {
                    case NAME:
                        strContainer = reader.getElementText();
                        candyEntity.setName(strContainer);
                        break;
                    case ENERGY:
                        strContainer = reader.getElementText();
                        int energyValue = Integer.parseInt(strContainer);
                        candyEntity.setEnergy(energyValue);
                        break;
                    case TYPE:
                        Type candyEntityType = new Type();
                        if (reader.getAttributeCount() == 1 && reader.getAttributeName(0).toString().equals(XmlCandyTag.FILLING.getTagValue())) {
                            strContainer = reader.getAttributeValue(0);
                            Filling filling = Filling.valueOf(strContainer);
                            candyEntityType.setFilling(filling);
                        } else {
                            throw new XmlParserAppException("count of Type attributes in xml file != 1 and not correct");
                        }
                        reader.next();
                        reader.next();
                        strContainer = reader.getElementText();
                        CandyType candyType = CandyType.valueOf(strContainer);
                        candyEntityType.setCandyType(candyType);
                        candyEntity.setType(candyEntityType);
                        break;
                    case INGREDIENTS:
                        buildEntityIngredientList(reader, candyEntity);
                        break;
                    case VALUE:
                        int containerValue;
                        boolean valueBuildCompleted = false;
                        Value value = new Value();
                        String valueTagName = XmlCandyTag.VALUE.getTagValue();
                        while (!valueBuildCompleted) {
                            type = reader.next();
                            if (type == XMLStreamConstants.START_ELEMENT || type == XMLStreamConstants.END_ELEMENT) {
                                tagName = reader.getLocalName();
                            }
                            if (type == XMLStreamConstants.START_ELEMENT) {
                                strContainer = reader.getElementText();
                                System.out.println(tagName);
                                XmlCandyTag xmlCandyTag = XmlCandyTag.valueOf(tagName.toUpperCase());
                                switch (xmlCandyTag) {
                                    case PROTEINS_WEIGHT:
                                        containerValue = Integer.parseInt(strContainer);
                                        value.setProteinsWeight(containerValue);
                                        break;
                                    case FATS_WEIGHT:
                                        containerValue = Integer.parseInt(strContainer);
                                        value.setFatsWeight(containerValue);
                                        break;
                                    case CARBOHYDRATES_WEIGHT:
                                        containerValue = Integer.parseInt(strContainer);
                                        value.setCarbohydratesWeight(containerValue);
                                        break;
                                }
                            } else if (type == XMLStreamConstants.END_ELEMENT && tagName.equals(valueTagName)) {
                                valueBuildCompleted = true;
                            }
                        }
                        candyEntity.setValue(value);
                        break;
                    case PRODUCTION:
                        strContainer = reader.getElementText();
                        candyEntity.setProduction(strContainer);
                        break;
                    case CREATION_DATA:
                        strContainer = reader.getElementText();
                        int year = Integer.parseInt(strContainer.substring(0, 4));
                        int month = Integer.parseInt(strContainer.substring(5, 7));
                        int day = Integer.parseInt(strContainer.substring(8, 10));
                        LocalDate creationDate = LocalDate.of(year, month, day);
                        candyEntity.setCreationDate(creationDate);
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT && tagName.equals(XmlCandyTag.CANDY_ENTITY.getTagValue())) {
                entityBuildCompleted = true;
            }
            tagName = "";
        }
        return candyEntity;
    }

    private void buildEntityIngredientList(XMLStreamReader reader, CandyEntity candyEntity) throws XMLStreamException, XmlParserAppException {
        boolean ingredientsBuildCompleted = false;
        boolean currentIngredientCompleted = false;
        int type;
        String tagName = "";
        String strContainer;
        Ingredient ingredient = null;
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        reader.next();
        while (!ingredientsBuildCompleted) {
            type = reader.next();
            if (reader.isStartElement() || reader.isEndElement()) {
                tagName = reader.getLocalName();
            }
            if (type == XMLStreamConstants.START_ELEMENT && tagName.equals(XmlCandyTag.INGREDIENT.getTagValue())) {
                ingredient = new Ingredient();
                currentIngredientCompleted = false;
                while (!currentIngredientCompleted) {
                    type = reader.next();
                    if (reader.isStartElement()) {
                        tagName = reader.getLocalName();
                    }
                    if (tagName.equals(XmlCandyTag.INGREDIENT_TYPE.getTagValue())) {
                        strContainer = reader.getElementText();
                        IngredientType ingredientType = IngredientType.valueOf(strContainer);
                        ingredient.setIngredientType(ingredientType);
                    } else if (tagName.equals(XmlCandyTag.INGREDIENT_WEIGHT.getTagValue())) {
                        strContainer = reader.getElementText();
                        int ingredientWeight = Integer.parseInt(strContainer);
                        ingredient.setIngredientWeight(ingredientWeight);
                    } else if (type == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals(XmlCandyTag.INGREDIENT.getTagValue())) {
                        ingredientList.add(ingredient);
                        currentIngredientCompleted = true;
                    }
                    tagName = "";
                }
            } else if (type == XMLStreamConstants.END_ELEMENT && tagName.equals(XmlCandyTag.INGREDIENTS.getTagValue())) {
                ingredientsBuildCompleted = true;
            }
        }
        candyEntity.setIngredients(ingredientList);
    }

    public HashSet getCandyEntityHashSet() throws XmlParserAppException {
        if (buildCompleted == false) {
            throw new XmlParserAppException("CandyStaxEntityBuilderImpl.class : getCandyEntityHashSet() : build is not complete.");
        }
        return this.candyHashSet;
    }

    public boolean isBuildCompleted() {
        return buildCompleted;
    }
}
