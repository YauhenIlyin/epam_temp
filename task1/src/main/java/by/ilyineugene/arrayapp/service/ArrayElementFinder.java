package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.exception.OperationArrayException;

public interface ArrayElementFinder<T extends Number> {

    public Double findMinValue(T[] array) throws OperationArrayException;

    public Double findMaxValue(T[] array) throws OperationArrayException;
}
