package by.ilyineugene.xmlparser.validator;

import by.ilyineugene.xmlparser.handler.EntityErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlXsdValidator {

    public static boolean isValid(String language, String xmlFilePathName, String xsdSchemaPathName) {
        File schemaLocation = new File(xsdSchemaPathName);
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Source source = new StreamSource(xmlFilePathName);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new EntityErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            //e.printStackTrace();  //write log here
            return false;
        }
        return true;
    }

}
