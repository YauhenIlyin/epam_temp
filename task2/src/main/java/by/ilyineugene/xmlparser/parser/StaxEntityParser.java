package by.ilyineugene.xmlparser.parser;

import by.ilyineugene.xmlparser.builder.sax.SaxEntityBuilder;
import by.ilyineugene.xmlparser.builder.stax.StaxEntityBuilder;
import by.ilyineugene.xmlparser.builder.stax.impl.CandyStaxEntityBuilderImpl;
import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.evidence.XmlPropertyKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.factory.BuilderVersionFactory;
import by.ilyineugene.xmlparser.util.PropertiesXmlOperator;
import by.ilyineugene.xmlparser.validator.XmlXsdValidator;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.HashSet;

public class StaxEntityParser extends CommonEntityParser {

    private StaxEntityBuilder builder;

    public HashSet parseXmlFile(XmlPropertyKeyWordName propertyKeyWordName) throws XmlParserAppException {
        initialiseBuilder(propertyKeyWordName);
        PropertiesXmlOperator po = PropertiesXmlOperator.getInstance();
        String xmlPathName = po.getXmlFilePathName(propertyKeyWordName.getNameValue());
        String xsdPathName = po.getXsdFilePathName(propertyKeyWordName.getNameValue());
        preliminaryValidation(xmlPathName, xsdPathName);
        File file = new File(xmlPathName);
        FileInputStream inputStream;
        XMLStreamReader reader;
        try {
            inputStream = new FileInputStream(file);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException e) {
            throw new XmlParserAppException("StaxEntityParser: parseXmlFile(XmlPropertyKeyWordName x): FileNotFoundException in FileInputStream(file)");
        } catch (XMLStreamException e) {
            throw new XmlParserAppException("StaxEntityParser: parseXmlFile(XmlPropertyKeyWordName x): XMLStreamException in createXmlStreamReader(inputStream))");
        }
        builder.parseAndBuildEntityHashSet(reader);
        return builder.getCandyEntityHashSet();
    }

    private void initialiseBuilder(XmlPropertyKeyWordName xmlPropertyKeyWordName) throws XmlParserAppException {
        this.builder = (StaxEntityBuilder) BuilderVersionFactory.createBuilder(xmlPropertyKeyWordName, XmlParserKeyWordName.STAX);
    }

    private void preliminaryValidation(String xmlPathName, String xsdPathName) throws XmlParserAppException {
        boolean isValid = XmlXsdValidator.isValid(XMLConstants.W3C_XML_SCHEMA_NS_URI, xmlPathName, xsdPathName);
        if (!isValid) {
            throw new XmlParserAppException("CandySaxEntityBuilderImpl.class preliminaryValidation(): xml or xsd files is not valid. can't build it. " +
                    "xml: " + xmlPathName + " xsd: " + xsdPathName);
        }
    }

}
