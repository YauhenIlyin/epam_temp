package by.ilyineugene.arrayapp.parser.impl;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.reader.ArrayReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayParserImplTest {

    ArrayParserImpl arrayParser;

    @BeforeClass
    public void setUP() {
        arrayParser = new ArrayParserImpl();
    }

    @AfterClass
    public void tearDown() {
        arrayParser = null;
    }

    @DataProvider(name = "positiveStringDataProvider1")
    public Object[][] createStringArrayData1() {
        return new Object[][]{
                {"22 33 44 55 ", new Number[] {22.0,33.0,44.0,55.0}},
                {"22 33 44 55", new Number[] {22.0,33.0,44.0,55.0}},
                {" 22 33   -- 44-- - - - 55 ", new Number[] {22.0,33.0,44.0,55.0}},
        };
    }

    @Test(dataProvider = "positiveStringDataProvider1", enabled = true)
    public void fileArrayReaderTest1(String string, Number[] expected) throws OperationArrayException {
        Number[] actual = arrayParser.stringToArrayNumberParser(string);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }
}
