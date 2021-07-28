package by.ilyineugene.arrayapp.reader;

import by.ilyineugene.arrayapp.entity.EntityArray;
import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.impl.ArrayElementCalculatorImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ArrayReaderTest {
    ArrayReader arrayReader;

    @BeforeClass
    public void setUP() {
        arrayReader = new ArrayReader();
    }

    @AfterClass
    public void tearDown() {
        arrayReader = null;
    }

    @DataProvider(name = "positiveFilePathDataProvider1")
    public Object[][] createFilePathData1() {
        return new Object[][]{
                {"src/test/java/resources/data/arraydata.txt", "17.3 22.4  5.0 21.12 "},
        };
    }

    @Test(dataProvider = "positiveFilePathDataProvider1", enabled = true)
    public void fileArrayReaderTest1(String filePath, String expected) throws OperationArrayException {

        String actual = arrayReader.fileArrayReader(filePath);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }

    @DataProvider(name = "positiveFilePathDataProvider2")
    public Object[][] createFilePathData2() {
        return new Object[][]{
                {"src/test/java/resources/data/arraydata1.txt", "4.7 5.2 3.4 6.3"},
        };
    }

    @Test(dataProvider = "positiveFilePathDataProvider2", enabled = true)
    public void fileArrayReaderTest2(String filePath, String expected) throws OperationArrayException {
        String actual = arrayReader.fileArrayReader(filePath);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }

    @DataProvider(name = "filePathExceptionProvider1")
    public Object[][] createFilePathData3() {
        return new Object[][]{
                {"src/test/java/resources/data/arraydata3.txt", "4.7 5.2 3.4 6.3"},
        };
    }

    @Test(dataProvider = "filePathExceptionProvider1", expectedExceptions = OperationArrayException.class, expectedExceptionsMessageRegExp = "there are no valid lines in the file...", enabled = true)
    public void fileArrayReaderTest3(String filePath, String expected) throws OperationArrayException {
        String actual = arrayReader.fileArrayReader(filePath);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }
}
