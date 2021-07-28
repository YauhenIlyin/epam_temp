package by.ilyineugene.arrayapp.service.impl;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.service.ArrayElementReplacer;
import by.ilyineugene.arrayapp.validator.ArrayValidator;

public class ArrayElementReplacerImpl <T extends Number> implements ArrayElementReplacer {


    @Override
    public void replaceElementByIndex(Number[] array, Number newElement, int index) throws OperationArrayException {
        if(ArrayValidator.isValid(array) && index >= 0 && index <= array.length) {
            array[index] = newElement;
        } else {
            throw new OperationArrayException("invalid array or index are out of bounds of array");
        }
    }

    @Override
    public void swapTwoElementsByIndexes(Number[] array, int firstIndex, int secondIndex) throws OperationArrayException {
        if(ArrayValidator.isValid(array) && firstIndex >= 0 && firstIndex < array.length && secondIndex >= 0 && secondIndex < array.length) {
            Number container = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = container;
        } else {
            throw new OperationArrayException("invalid array or indexes are out of bounds of array");
        }
    }
}
