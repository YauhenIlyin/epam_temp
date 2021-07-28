package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.entity.EntityArray;
import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.impl.ArrayElementCalculatorImpl;
import by.ilyineugene.arrayapp.service.impl.ArrayElementSorterImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayElementSorterImplTest {
    ArrayElementSorterImpl arrayElementSorterImpl;
    EntityArray entityArray;

    @BeforeClass
    public void setUP() {
        arrayElementSorterImpl = new ArrayElementSorterImpl();
    }

    @AfterClass
    public void tearDown() {
        arrayElementSorterImpl = null;
        entityArray = null;
    }

    @DataProvider(name = "positiveDoubleDataProvider")
    public Object[][] createDoubleData() {
        return new Object[][]{
                {new Double[]{3.5, 4.5, 4., 2.1, 3.2}, new Double[]{2.1, 3.2, 3.5, 4., 4.5}},
                {new Double[]{1., 2., 1.}, new Double[]{1., 1., 2.}},
                {new Double[]{0., 0., 0.}, new Double[]{0., 0., 0.}},
                {new Double[]{-1.3, -0.7, -1.}, new Double[]{-1.3, -1., -0.7}}
                //можно ли отходить от норм "правописания", чтобы в таких местах делать код читабельнее? или это неправильно?
        };
    }

    @Test(dataProvider = "positiveDoubleDataProvider", enabled = true)
    public void bubbleSortTest1(Double[] array, Double[] expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double[] actual = entityArray.getArray();
        arrayElementSorterImpl.bubbleSort(actual);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }

    @Test(dataProvider = "positiveDoubleDataProvider", enabled = true)
    public void shakerSortTest1(Double[] array, Double[] expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double[] actual = entityArray.getArray();
        arrayElementSorterImpl.shakerSort(actual);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }

    @Test(dataProvider = "positiveDoubleDataProvider", enabled = true)
    public void selectSortTest1(Double[] array, Double[] expected) throws OperationArrayException {
        EntityArray<Double> entityArray = new EntityArray<>(array);
        Double[] actual = entityArray.getArray();
        arrayElementSorterImpl.selectSort(actual);
        assertEquals(actual, expected, "AverageValue of array test failure...");
    }

}
