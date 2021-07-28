package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.entity.EntityArray;
import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.impl.ArrayElementFinderImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayElementFinderImplTest {
    ArrayElementFinderImpl arrayElementFinderImpl = new ArrayElementFinderImpl();
    EntityArray entityArray;

    @BeforeClass
    public void setUP() {
        arrayElementFinderImpl = new ArrayElementFinderImpl();
    }

    @AfterClass
    public void tearDown() {
        arrayElementFinderImpl = null;
        entityArray = null;
    }

    @DataProvider(name = "positiveDoubleDataProviderMin")
    public Object[][] createDoubleData() {
        return new Object[][]{
                {new Double[]{3.5, 4.5, 4.}, 3.5},
                {new Double[]{1., 2., 6.}, 1.},
                {new Double[]{0., 0., 0.}, 0.},
                {new Double[]{-1.3, -0.7, -1.}, -1.3}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "positiveDoubleDataProviderMin", enabled = true)
    public void findMinValueTest(Double[] array, Double expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double actual = arrayElementFinderImpl.findMinValue(entityArray.getArray());
        assertEquals(actual.doubleValue(), expected.doubleValue(), 0.001, "minValue of array test failure...");
    }

    @DataProvider(name = "positiveDoubleDataProviderMax")
    public Object[][] createDoubleDataPositiveMax() {
        return new Object[][]{
                {new Double[]{1.5, 9.3, 4.}, 9.3},
                {new Double[]{1.5}, 1.5},
                {new Double[]{1., 2., 6.}, 6.},
                {new Double[]{10., 2., 6.}, 10.},
                {new Double[]{0., 0., 0.}, 0.},
                {new Double[]{-1.3, -0.7, -1.}, -0.7}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "positiveDoubleDataProviderMax", enabled = true)
    public void findMaxValueTest(Double[] array, Double expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double actual = arrayElementFinderImpl.findMaxValue(entityArray.getArray());
        assertEquals(actual.doubleValue(), expected.doubleValue(), 0.001, "maxValue of array test failure...");
    }
}
