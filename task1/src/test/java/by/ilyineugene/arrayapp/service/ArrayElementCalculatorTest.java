package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.entity.EntityArray;
import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.impl.ArrayElementCalculatorImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayElementCalculatorTest {
    ArrayElementCalculatorImpl arrayElementCalculatorImpl;
    EntityArray entityArray;

    @BeforeClass
    public void setUP() {
        arrayElementCalculatorImpl = new ArrayElementCalculatorImpl();
    }

    @AfterClass
    public void tearDown() {
        arrayElementCalculatorImpl = null;
        entityArray = null;
    }

    @DataProvider(name = "positiveDoubleDataProvider")
    public Object[][] createDoubleData1() {
        return new Object[][]{
                {new Double[]{3.5, 4.5, 4.}, 4.},
                {new Double[]{1., 2., 6.}, 3.},
                {new Double[]{0., 0., 0.}, 0.},
                {new Double[]{-1.3, -0.7, -1.}, -1.}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "positiveDoubleDataProvider", enabled = true)
    public void calculateAverageValueTest1(Double[] array, Double expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double actual = arrayElementCalculatorImpl.calculateAverageValue(entityArray.getArray());
        assertEquals(actual.doubleValue(), expected.doubleValue(), 0.001, "AverageValue of array test failure...");
    }

    @DataProvider(name = "positiveIntegerDataProvider")
    public Object[][] createIntegerData() {
        return new Object[][]{
                {new Integer[]{1, 2, 3}, 2},
                {new Integer[]{1, 2, 6}, 3},
                {new Integer[]{0, 0, 0}, 0},
                {new Integer[]{-1, -7, -1}, -3}
        };
        //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
    }

    @Test(dataProvider = "positiveIntegerDataProvider", enabled = true)
    public void calculateAverageValueTest2(Integer[] array, Integer expected) throws OperationArrayException {
        EntityArray<Integer> entityArray = new EntityArray<>(array);
        Double actual = arrayElementCalculatorImpl.calculateAverageValue(entityArray.getArray());
        assertEquals(actual.intValue(), expected.intValue(), 0.001, "AverageValue of array test failure...");
    }

    @DataProvider(name = "errorDataProvider")
    public Object[][] createErrorData() {
        return new Object[][]{
                {null, 2}, {new Integer[0], 0},
        };
    }

    @Test(dataProvider = "errorDataProvider", expectedExceptions = OperationArrayException.class, expectedExceptionsMessageRegExp = "count error. it is impossible to find the average of 0 elements or array is null", enabled = true)
    public void calculateAverageValueTest3(Integer[] array, Integer expected) throws OperationArrayException {
        EntityArray<Integer> entityArray = new EntityArray<>(array);
        Double actual = arrayElementCalculatorImpl.calculateAverageValue(entityArray.getArray());
        assertEquals(actual.intValue(), expected.intValue(), 0.001, "AverageValue of array test failure...");
    }

    @DataProvider(name = "negativeDoubleDataProvider")
    public Object[][] createDoubleData2() {
        return new Object[][]{
                {new Double[]{3.5, 4.5, 4.}, 22.},
                {new Double[]{1., 2., 6.}, -17.},
                {new Double[]{0., 0., 0.}, 5.},
                {new Double[]{-1.3, -0.7, -1.}, 100.}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "negativeDoubleDataProvider", enabled = true)
    public void calculateAverageValueTest2(Double[] array, Double expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double actual = arrayElementCalculatorImpl.calculateAverageValue(entityArray.getArray());
        assertNotEquals(actual.doubleValue(), expected.doubleValue(), 0.001, "AverageValue of array test failure...");
    }

    @DataProvider(name = "positiveDoubleDataProviderSum")
    public Object[][] createDoubleData() {
        return new Object[][]{
                {new Double[]{3.5, 4.5, 4.}, 12.},
                {new Double[]{1., 2., 6.}, 9.},
                {new Double[]{0., 0., 0.}, 0.},
                {new Double[]{-1.3, -0.7, -1.}, -3.}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "positiveDoubleDataProviderSum", enabled = true)
    public void calculateSumValueTest(Double[] array, Double expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double actual = arrayElementCalculatorImpl.calculateSumValue(entityArray.getArray());
        assertEquals(actual.doubleValue(), expected.doubleValue(), 0.001, "AverageValue of array test failure...");
    }

}
