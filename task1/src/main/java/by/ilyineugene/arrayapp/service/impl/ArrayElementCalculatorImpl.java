package by.ilyineugene.arrayapp.service.impl;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.ArrayElementCalculator;
import by.ilyineugene.arrayapp.validator.ArrayValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ArrayElementCalculatorImpl<T extends Number> implements ArrayElementCalculator {

    static Logger logger = LogManager.getLogger("ConsoleLog");

    @Override
    public Double calculateAverageValue(Number[] array) throws OperationArrayException {
        if (ArrayValidator.isValid(array)) {
            double sum = 0;
            for (int index = 0; index < array.length; index++) {
                sum += array[index].doubleValue();
            }
            logger.log(Level.DEBUG, "return average value of array");
            return sum / array.length;
        } else {
            throw new OperationArrayException("count error. it is impossible to find the average of 0 elements or array is null");
        }
    }

    @Override
    public Double calculateSumValue(Number[] array) throws OperationArrayException {
        if (ArrayValidator.isValid(array)) {
            double sum = 0;
            for (int index = 0; index < array.length; index++) {
                sum = sum + array[index].doubleValue();
            }
            return sum;
        } else {
            throw new OperationArrayException("invalid array");
        }
    }

    @Override
    public int calculatePositiveElementsCount(Number[] array) throws OperationArrayException {
        if (ArrayValidator.isValid(array)) {
            int countOfPositiveElements = 0;
            for (int index = 0; index < array.length; index++) {
                if (array[index].doubleValue() > 0) {
                    countOfPositiveElements++;
                }
            }
            return countOfPositiveElements;
        } else {
            throw new OperationArrayException("invalid array");
        }
    }

    @Override
    public int calculateNegativeElementsCount(Number[] array) throws OperationArrayException {
        if (ArrayValidator.isValid(array)) {
            int countOfNegativeElements = 0;
            for (int index = 0; index < array.length; index++) {
                if (array[index].doubleValue() < 0) {
                    countOfNegativeElements++;
                }
            }
            return countOfNegativeElements;
        } else {
            throw new OperationArrayException("invalid array");
        }
    }
}
