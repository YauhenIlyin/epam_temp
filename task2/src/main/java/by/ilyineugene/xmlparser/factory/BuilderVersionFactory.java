package by.ilyineugene.xmlparser.factory;

import by.ilyineugene.xmlparser.builder.EntityBuilder;
import by.ilyineugene.xmlparser.builder.dom.impl.CandyDomEntityBuilderImpl;
import by.ilyineugene.xmlparser.builder.sax.impl.CandySaxEntityBuilderImpl;
import by.ilyineugene.xmlparser.builder.stax.impl.CandyStaxEntityBuilderImpl;
import by.ilyineugene.xmlparser.evidence.XmlParserKeyWordName;
import by.ilyineugene.xmlparser.evidence.XmlPropertyKeyWordName;
import by.ilyineugene.xmlparser.exception.XmlParserAppException;

public class BuilderVersionFactory {

    public static EntityBuilder createBuilder(XmlPropertyKeyWordName propertyKeyWordName, XmlParserKeyWordName parserKeyWordName) throws XmlParserAppException {
        if (propertyKeyWordName == null || parserKeyWordName == null) {
            throw new XmlParserAppException("incorrect propertyKeyWordName = null or xmlParserKeyWordName = null");
        }
        EntityBuilder entityBuilder = null; // ?? requires initialization of return value
        if (parserKeyWordName.equals(XmlParserKeyWordName.SAX)) {
            switch (propertyKeyWordName) {
                case CANDY:
                    entityBuilder = new CandySaxEntityBuilderImpl();
            }
        }
        if (parserKeyWordName.equals(XmlParserKeyWordName.STAX)) {
            switch (propertyKeyWordName) {
                case CANDY:
                    entityBuilder = new CandyStaxEntityBuilderImpl();
            }
        }
        if (parserKeyWordName.equals(XmlParserKeyWordName.DOM)) {
            switch (propertyKeyWordName) {
                case CANDY:
                    entityBuilder = new CandyDomEntityBuilderImpl();
            }
        }
        return entityBuilder;
    }
}
