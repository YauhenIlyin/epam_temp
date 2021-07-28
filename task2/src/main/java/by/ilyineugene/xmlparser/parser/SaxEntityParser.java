package by.ilyineugene.xmlparser.parser;

import by.ilyineugene.xmlparser.builder.sax.SaxEntityBuilder;
import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.evidence.XmlPropertyKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.factory.BuilderVersionFactory;
import by.ilyineugene.xmlparser.handler.EntityErrorHandler;
import by.ilyineugene.xmlparser.handler.EntityHandler;
import by.ilyineugene.xmlparser.util.PropertiesXmlOperator;
import by.ilyineugene.xmlparser.validator.XmlXsdValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;

public class SaxEntityParser extends CommonEntityParser {

    private SaxEntityBuilder builder;

    public HashSet parseXmlFile(XmlPropertyKeyWordName propertyKeyWordName) throws XmlParserAppException {
        initialiseBuilder(propertyKeyWordName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        PropertiesXmlOperator po = PropertiesXmlOperator.getInstance();
        String xmlPathName = po.getXmlFilePathName(propertyKeyWordName.getNameValue());
        String xsdPathName = po.getXsdFilePathName(propertyKeyWordName.getNameValue());
        try {
            SAXParser parser = factory.newSAXParser();
            EntityHandler entityHandler = new EntityHandler(builder);
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(entityHandler);
            reader.setErrorHandler(new EntityErrorHandler());
            preliminaryValidation(xmlPathName, xsdPathName);
            reader.parse(xmlPathName);
        } catch (ParserConfigurationException e) {
            throw new XmlParserAppException("SaxEntityParser.class parseXmlFile(XmlPropertyKeyWordName, XmlParserKeyWordName): ParserConfiguration exception..");
        } catch (SAXException e) {
            throw new XmlParserAppException("SaxEntityParser.class parseXmlFile(XmlPropertyKeyWordName, XmlParserKeyWordName): SaxException exception..");
        } catch (IOException e) {
            throw new XmlParserAppException("SaxEntityParser.class parseXmlFile(XmlPropertyKeyWordName, XmlParserKeyWordName): reader error..");
        }
        return builder.getEntityHashSet();
    }

    private void initialiseBuilder(XmlPropertyKeyWordName propertyKeyWordName) throws XmlParserAppException {
        this.builder = (SaxEntityBuilder) BuilderVersionFactory.createBuilder(propertyKeyWordName, XmlParserKeyWordName.SAX);
    }

    private void preliminaryValidation(String xmlPathName, String xsdPathName) throws XmlParserAppException {
        boolean isValid = XmlXsdValidator.isValid(XMLConstants.W3C_XML_SCHEMA_NS_URI, xmlPathName, xsdPathName);
        if (!isValid) {
            throw new XmlParserAppException("CandySaxEntityBuilderImpl.class preliminaryValidation(): xml or xsd files is not valid. can't build it. " +
                    "xml: " + xmlPathName + " xsd: " + xsdPathName);
        }
    }
}
