package by.ilyineugene.xmlparser.builder.stax;

import by.ilyineugene.xmlparser.exception.XmlParserAppException;

import javax.xml.stream.XMLStreamReader;
import java.util.HashSet;

public interface StaxEntityBuilder {

    public void parseAndBuildEntityHashSet(XMLStreamReader reader) throws XmlParserAppException;

    public HashSet getCandyEntityHashSet() throws XmlParserAppException;

    public boolean isBuildCompleted();

}
