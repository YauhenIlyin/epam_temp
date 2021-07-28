package by.ilyineugene.xmlparser.builder.sax;

import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import org.xml.sax.Attributes;

import java.util.HashSet;

public interface SaxEntityBuilder {

    public abstract void startBuildElement(String str, Attributes attrs);

    public abstract void endBuildElement(String str);

    public abstract void buildElementValue(String data);

    public abstract HashSet getEntityHashSet() throws XmlParserAppException;
}
