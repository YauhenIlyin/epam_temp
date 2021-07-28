package by.ilyineugene.xmlparser.main;

import by.ilyineugene.xmlparser.entity.candy.CandyEntity;
import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.evidence.XmlPropertyKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.factory.ParserFactory;
import by.ilyineugene.xmlparser.parser.DomEntityParser;
import by.ilyineugene.xmlparser.parser.SaxEntityParser;
import by.ilyineugene.xmlparser.parser.StaxEntityParser;
import by.ilyineugene.xmlparser.util.PropertiesXmlOperator;
import by.ilyineugene.xmlparser.validator.XmlXsdValidator;

import javax.xml.XMLConstants;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws XmlParserAppException {
        PropertiesXmlOperator po = PropertiesXmlOperator.getInstance();
        String xmlFilePathName = po.getXmlFilePathName(XmlPropertyKeyWordName.CANDY.getNameValue());
        String xsdFilePathName = po.getXsdFilePathName(XmlPropertyKeyWordName.CANDY.getNameValue());
        XmlXsdValidator.isValid(XMLConstants.W3C_XML_SCHEMA_NS_URI, xmlFilePathName, xsdFilePathName);


        SaxEntityParser saxEntityParser = (SaxEntityParser) ParserFactory.createParser(XmlParserKeyWordName.SAX);
        HashSet<CandyEntity> candyEntityHashSet1 = saxEntityParser.parseXmlFile(XmlPropertyKeyWordName.CANDY);
        System.out.println(candyEntityHashSet1.toString());
        System.out.println();


        DomEntityParser domEntityParser = (DomEntityParser) ParserFactory.createParser(XmlParserKeyWordName.DOM);
        HashSet<CandyEntity> candyEntityHashSet2 = domEntityParser.parseXmlFile(XmlPropertyKeyWordName.CANDY);
        System.out.println(candyEntityHashSet2.toString());
        System.out.println();


        StaxEntityParser staxEntityParser = (StaxEntityParser) ParserFactory.createParser(XmlParserKeyWordName.STAX);
        HashSet<CandyEntity> candyEntityHashSet3 = staxEntityParser.parseXmlFile(XmlPropertyKeyWordName.CANDY);
        System.out.println(candyEntityHashSet3.toString());

    }

}