package by.ilyineugene.xmlparser.validator;

import by.ilyineugene.xmlparser.exception.XmlParserAppException;
import by.ilyineugene.xmlparser.handler.EntityErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class BaseValidator {

    private static String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static String filePath = "src/main/resources/data/xml/candy/candies.xml";
    private static String schemaFilePath = "src/main/resources/data/xml/candy/candies.xsd";

    public static void main(String[] args) throws XmlParserAppException {

        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemaFilePath);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.setErrorHandler(new EntityErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            throw new XmlParserAppException("SAX or IO exception in BaseValidator...");
        }
    }
}
