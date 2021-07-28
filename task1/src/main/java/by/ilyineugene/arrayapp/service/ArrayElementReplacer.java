package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.exception.OperationArrayException;

public interface ArrayElementReplacer<T extends Number> {

    public void replaceElementByIndex(T[] array, T newElement, int index) throws OperationArrayException;

    public void swapTwoElementsByIndexes(T[] array, int firstIndex, int secondIndex) throws OperationArrayException;

}
