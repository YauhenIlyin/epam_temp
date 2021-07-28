package by.ilyineugene.xmlparser.util;

import by.ilyineugene.xmlparser.exception.XmlParserAppException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class PropertiesXmlOperator {

    private final String PROPERTIES_FILE_PATH = "src/main/resources/xml.properties";
    private final String SECOND_PART_XML_PROPERTY_NAME = "XmlFilePathName";
    private final String SECOND_PART_XSD_PROPERTY_NAME = "XsdFilePathName";
    private Properties properties;

    private static AtomicBoolean isInitialiseInstance = new AtomicBoolean(false);
    private static PropertiesXmlOperator instance;

    private PropertiesXmlOperator() throws XmlParserAppException {
        initialiseProperties();
    }

    public static PropertiesXmlOperator getInstance() throws XmlParserAppException {
        while (instance == null) {
            if (isInitialiseInstance.compareAndSet(false, true)) {
                instance = new PropertiesXmlOperator();
            }
        }
        return instance;
    }

    public String getXmlFilePathName(String firstPartPropertyName) {
        return properties.getProperty(firstPartPropertyName + SECOND_PART_XML_PROPERTY_NAME);
    }

    public String getXsdFilePathName(String firstPartPropertyName) {
        return properties.getProperty(firstPartPropertyName + SECOND_PART_XSD_PROPERTY_NAME);
    }

    private void initialiseProperties() throws XmlParserAppException {
        try {
            FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH);
            Properties properties = new Properties();
            properties.load(fis);
            this.properties = properties;
        } catch (FileNotFoundException e) {
            throw new XmlParserAppException("PropertiesXmlOperator.class initialiseProperties(): xml.properties file not found");
        } catch (IOException e) {
            throw new XmlParserAppException("PropertiesXmlOperator.class initialiseProperties(): file FileInputStream exception in \"properties.load()\"");
        }
    }

}
