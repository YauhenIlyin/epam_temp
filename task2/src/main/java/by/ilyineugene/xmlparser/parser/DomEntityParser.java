package by.ilyineugene.xmlparser.parser;

import by.ilyineugene.xmlparser.builder.dom.DomEntityBuilder;
import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.evidence.XmlPropertyKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.factory.BuilderVersionFactory;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

import by.ilyineugene.xmlparser.util.PropertiesXmlOperator;
import by.ilyineugene.xmlparser.validator.XmlXsdValidator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomEntityParser extends CommonEntityParser {
    private DomEntityBuilder builder;
    private DocumentBuilder docBuilder;

    public HashSet parseXmlFile(XmlPropertyKeyWordName propertyKeyWordName) throws XmlParserAppException {
        initialiseBuilder(propertyKeyWordName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        PropertiesXmlOperator po = PropertiesXmlOperator.getInstance();
        String xmlPathName = po.getXmlFilePathName(propertyKeyWordName.getNameValue());
        String xsdPathName = po.getXsdFilePathName(propertyKeyWordName.getNameValue());
        Document document;
        try {
            docBuilder = factory.newDocumentBuilder();
            preliminaryValidation(xmlPathName, xsdPathName);
            document = docBuilder.parse(xmlPathName);
            builder.buildEntityHashSet(document);
        } catch (SAXException | IOException e) {
            throw new XmlParserAppException("DomEntityParser.class parseXmlFile(XmlPropertyKeyWordName) : docBuilder.parse() error");
        } catch (ParserConfigurationException e) {
            throw new XmlParserAppException("DomEntityParser.class parseXmlFile(XmlPropertyKeyWordName) : ParserConfigurationError with factory.newDocumentBuilder() ");
        }
        return builder.getEntityHashSet();
    }

    private void initialiseBuilder(XmlPropertyKeyWordName propertyKeyWordName) throws XmlParserAppException {
        this.builder = (DomEntityBuilder) BuilderVersionFactory.createBuilder(propertyKeyWordName, XmlParserKeyWordName.DOM);
    }

    private void preliminaryValidation(String xmlPathName, String xsdPathName) throws XmlParserAppException {
        boolean isValid = XmlXsdValidator.isValid(XMLConstants.W3C_XML_SCHEMA_NS_URI, xmlPathName, xsdPathName);
        if (!isValid) {
            throw new XmlParserAppException("CandySaxEntityBuilderImpl.class preliminaryValidation(): xml or xsd files is not valid. can't build it. " +
                    "xml: " + xmlPathName + " xsd: " + xsdPathName);
        }
    }
}
