package by.ilyineugene.xmlparser.handler;

//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class EntityConsoleContentHandler extends DefaultHandler {
    // private static Logger logger = LogManager.getLogger("ConsoleLog");

    @Override
    public void startDocument() {
        // logger.log(Level.INFO, "EntityConsoleContentHandler : start document...");
        System.out.println("parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String tagData = qName + " ";
        for (int i = 0; i < attrs.getLength(); i++) {
            tagData += attrs.getQName(i) + "=" + attrs.getValue(i);
        }
        System.out.println(tagData);
    }

    public void characters(char[] ch, int start, int length) {
        System.out.print(new String(ch, start, length));
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.println(" " + qName);
    }

    public void endDocument() {
        System.out.println("\nParsing ended");
    }
}
