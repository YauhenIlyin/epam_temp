package by.ilyineugene.arrayapp.service.impl;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.ArrayElementFinder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayElementFinderImpl<T extends Number> implements ArrayElementFinder {
    static Logger logger = LogManager.getLogger("ConsoleLog");

    public Double findMinValue (Number[]array) throws OperationArrayException {
        if (array == null || array.length == 0) {
            throw new OperationArrayException("count error. it is impossible to find the min element of 0 elements or array is null");
        }
        double minValue = array[0].doubleValue();
        for (int index = 1; index < array.length; index++) {
            if (array[index].doubleValue() < minValue) {
                minValue = array[index].doubleValue();
            }
        }
        logger.log(Level.DEBUG, "return min value of array");
        return minValue;
    }

    public Double findMaxValue(Number[] array) throws OperationArrayException {
        if (array == null || array.length == 0) {
            throw new OperationArrayException("count error. it is impossible to find the max element of 0 elements or array is null");
        }
        double maxValue = array[0].doubleValue();
        for (int index = 1; index < array.length; index++) {
            if (array[index].doubleValue() > maxValue) {
                maxValue = array[index].doubleValue();
            }
        }
        logger.log(Level.DEBUG, "return max value of array");
        return maxValue;
    }
}
