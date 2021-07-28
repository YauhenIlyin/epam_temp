package by.ilyineugene.xmlparser.handler;

import by.ilyineugene.xmlparser.builder.sax.SaxEntityBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class EntityHandler extends DefaultHandler {

    private SaxEntityBuilder builder;

    public EntityHandler(SaxEntityBuilder builder) {
        this.builder = builder;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        builder.startBuildElement(qName, attrs);
    }

    public void endElement(String uri, String localName, String qName) {
        builder.endBuildElement(qName);
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        builder.buildElementValue(data);
    }

}
