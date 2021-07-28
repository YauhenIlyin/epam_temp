package by.ilyineugene.xmlparser.builder.dom.impl;

import by.ilyineugene.xmlparser.builder.EntityBuilder;
import by.ilyineugene.xmlparser.builder.dom.DomEntityBuilder;
import by.ilyineugene.xmlparser.entity.candy.CandyEntity;
import by.ilyineugene.xmlparser.entity.candy.Ingredient;
import by.ilyineugene.xmlparser.entity.candy.Type;
import by.ilyineugene.xmlparser.entity.candy.Value;
import by.ilyineugene.xmlparser.evidence.candy.CandyType;
import by.ilyineugene.xmlparser.evidence.candy.Filling;
import by.ilyineugene.xmlparser.evidence.candy.IngredientType;
import by.ilyineugene.xmlparser.evidence.candy.XmlCandyTag;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import org.w3c.dom.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static by.ilyineugene.xmlparser.evidence.candy.XmlCandyTag.*;

public class CandyDomEntityBuilderImpl extends EntityBuilder implements DomEntityBuilder {

    private HashSet<CandyEntity> candyHashSet;
    private boolean buildCompleted = false;
    private final String STR_TEMP = "#text";

    public CandyDomEntityBuilderImpl() {
    }

    public void buildEntityHashSet(Document doc) {
        Node rootNode = doc.getDocumentElement();
        NodeList nodeList = rootNode.getChildNodes();
        int entityCount = nodeList.getLength();
        Node node;
        candyHashSet = new HashSet<>();
        for (int i = 0; i < entityCount; i++) {
            node = nodeList.item(i);
            if (!node.getNodeName().equals(STR_TEMP)) {
                CandyEntity candyEntity = buildEntity(node);
                candyHashSet.add(candyEntity);
            }
        }
        buildCompleted = true;
    }

    private CandyEntity buildEntity(Node node) {
        CandyEntity currentEntity = new CandyEntity();
        NodeList candyNodeList = node.getChildNodes();
        NodeList nodeListContainer;
        int nodeListContainerLength;
        Node nodeContainer;
        NamedNodeMap attrContainer;
        String strContainer;
        int nodesListLength = candyNodeList.getLength();
        attrContainer = node.getAttributes();
        strContainer = attrContainer.getNamedItem(ID.getTagValue()).getTextContent();
        String strId = stringValueCorrection(strContainer);
        currentEntity.setId(strId);
        for (int i = 0; i < nodesListLength; i++) {
            Node currentNode = candyNodeList.item(i);
            if (!currentNode.getNodeName().equals(STR_TEMP)) {
                String tagName = currentNode.getNodeName();
                XmlCandyTag candyTag = XmlCandyTag.valueOf(tagName.toUpperCase());
                switch (candyTag) {
                    case NAME:
                        strContainer = stringValueCorrection(currentNode.getTextContent());
                        currentEntity.setName(strContainer);
                        break;
                    case ENERGY:
                        strContainer = stringValueCorrection(currentNode.getTextContent());
                        currentEntity.setEnergy(Integer.parseInt(strContainer));
                        break;
                    case TYPE:
                        Type type = new Type();
                        attrContainer = currentNode.getAttributes();
                        nodeContainer = attrContainer.getNamedItem(FILLING.getTagValue());
                        strContainer = stringValueCorrection(nodeContainer.getTextContent());
                        Filling filling = Filling.valueOf(strContainer);
                        type.setFilling(filling);
                        strContainer = stringValueCorrection(currentNode.getTextContent());
                        CandyType candyType = CandyType.valueOf(strContainer);
                        type.setCandyType(candyType);
                        currentEntity.setType(type);
                        break;
                    case INGREDIENTS:
                        nodeListContainer = currentNode.getChildNodes();
                        nodeListContainerLength = nodeListContainer.getLength();
                        ArrayList<Ingredient> ingredientList = new ArrayList<>(nodeListContainerLength);
                        Node nodeIngredient;
                        String nodeName;
                        Ingredient ingredient = null;
                        for (int index = 0; index < nodeListContainerLength; index++) {
                            nodeIngredient = nodeListContainer.item(index);
                            nodeName = nodeIngredient.getNodeName();
                            boolean isCorrectNode = false;
                            if (nodeName.equals(INGREDIENT.getTagValue())) {
                                NodeList ingredientInnerList = nodeIngredient.getChildNodes();
                                int ingredientInnerListLength = ingredientInnerList.getLength();
                                for (int innerIndex = 0; innerIndex < ingredientInnerListLength; innerIndex++) {
                                    nodeContainer = ingredientInnerList.item(innerIndex);
                                    strContainer = stringValueCorrection(nodeContainer.getTextContent());
                                    if (nodeContainer.getNodeName().equals(INGREDIENT_TYPE.getTagValue()) && !nodeContainer.getTextContent().equals("")) {
                                        ingredient = new Ingredient();
                                        IngredientType ingredientType = IngredientType.valueOf(strContainer.toUpperCase());
                                        ingredient.setIngredientType(ingredientType);
                                        nodeContainer = ingredientInnerList.item(1);
                                    }
                                    strContainer = stringValueCorrection(nodeContainer.getTextContent());
                                    if (nodeContainer.getNodeName().equals(INGREDIENT_WEIGHT.getTagValue()) && !nodeContainer.getTextContent().equals("")) {
                                        isCorrectNode = true;
                                        int ingredientWeight = Integer.parseInt(strContainer);
                                        ingredient.setIngredientWeight(ingredientWeight);
                                    }
                                }
                            }
                            if (isCorrectNode) {
                                ingredientList.add(ingredient);
                            }
                        }
                        currentEntity.setIngredients(ingredientList);
                        break;
                    case VALUE:
                        nodeListContainer = currentNode.getChildNodes();
                        Value value = new Value();
                        nodeListContainerLength = nodeListContainer.getLength();
                        for (int index = 0; index < nodeListContainerLength; index++) {
                            nodeContainer = nodeListContainer.item(index);
                            String innerNodeName = nodeContainer.getNodeName();
                            int intContainerValue;
                            if (innerNodeName == CARBOHYDRATES_WEIGHT.getTagValue()) {
                                strContainer = stringValueCorrection(nodeContainer.getTextContent());
                                intContainerValue = Integer.parseInt(strContainer);
                                value.setCarbohydratesWeight(intContainerValue);
                            } else if (innerNodeName == FATS_WEIGHT.getTagValue()) {
                                strContainer = stringValueCorrection(nodeContainer.getTextContent());
                                intContainerValue = Integer.parseInt(strContainer);
                                value.setFatsWeight(intContainerValue);
                            } else if (innerNodeName == PROTEINS_WEIGHT.getTagValue()) {
                                strContainer = stringValueCorrection(nodeContainer.getTextContent());
                                intContainerValue = Integer.parseInt(strContainer);
                                value.setProteinsWeight(intContainerValue);
                            }
                        }
                        currentEntity.setValue(value);
                        break;
                    case PRODUCTION:
                        strContainer = stringValueCorrection(currentNode.getTextContent());
                        currentEntity.setProduction(strContainer);
                        break;
                    case CREATION_DATA:
                        strContainer = stringValueCorrection(currentNode.getTextContent());
                        int year = Integer.parseInt(strContainer.substring(0, 4));
                        int month = Integer.parseInt(strContainer.substring(5, 7));
                        int day = Integer.parseInt(strContainer.substring(8, 10));
                        LocalDate date = LocalDate.of(year, month, day);
                        currentEntity.setCreationDate(date);
                        break;
                }
            }
        }
        return currentEntity;
    }

    public HashSet getEntityHashSet() throws XmlParserAppException {
        if (buildCompleted == false) {
            throw new XmlParserAppException("CandyDomEntityBuilderImpl.class getEntityHashSet(): build is not complete. error..");
        }
        return this.candyHashSet;
    }

    public boolean isBuildCompleted() {
        return buildCompleted;
    }

    private String stringValueCorrection(String str) {
        String correctStr = str.replace("\n", "").replace("\r", "").replace("\t", "").trim();
        return correctStr;
    }
}
