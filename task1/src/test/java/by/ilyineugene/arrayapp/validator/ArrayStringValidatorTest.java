package by.ilyineugene.arrayapp.validator;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.validator.ArrayStringValidatorImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayStringValidatorTest {


    @DataProvider(name = "stringDataProvider1")
    public Object[][] createStringData1() {
        return new Object[][]{
                {"25 45 17 34 5 8", true},
                {"25 45 -17 34. 5 8", true},
                {"25 45 hh17 34 5 8", false},
                {"25 45 17 3h4 5 8", false}
        };
    }

    @Test(dataProvider = "stringDataProvider1", enabled = true)
    public void isValidTest1(String string, boolean expected) throws OperationArrayException {
        boolean actual = ArrayStringValidatorImpl.isValid(string);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }
}
