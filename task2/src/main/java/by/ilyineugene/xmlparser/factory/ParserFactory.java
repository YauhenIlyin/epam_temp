package by.ilyineugene.xmlparser.factory;

import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.parser.CommonEntityParser;
import by.ilyineugene.xmlparser.parser.DomEntityParser;
import by.ilyineugene.xmlparser.parser.SaxEntityParser;
import by.ilyineugene.xmlparser.parser.StaxEntityParser;

public class ParserFactory {

    public static CommonEntityParser createParser(XmlParserKeyWordName parserKeyWordName) throws XmlParserAppException {
        if (parserKeyWordName == null) {
            throw new XmlParserAppException("ParserFactory.class createParser(): XmlParserKeyWordName value is null");
        }
        CommonEntityParser entityParser = null; // initialise ?
        switch (parserKeyWordName) {
            case SAX:
                entityParser = new SaxEntityParser();
                break;
            case STAX:
                entityParser = new StaxEntityParser();
                break;
            case DOM:
                entityParser = new DomEntityParser();
                break;
        }
        return entityParser;
    }
}
