package by.ilyineugene.xmlparser.builder.dom;

import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import org.w3c.dom.Document;

import java.util.HashSet;

public interface DomEntityBuilder {

    public void buildEntityHashSet(Document doc);

    public HashSet getEntityHashSet() throws XmlParserAppException;

    public boolean isBuildCompleted();

}
