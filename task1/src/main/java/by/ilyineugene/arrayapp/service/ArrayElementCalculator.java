package by.ilyineugene.arrayapp.service;

import by.ilyineugene.arrayapp.exception.OperationArrayException;

public interface ArrayElementCalculator <T extends Number>{

    public Double calculateAverageValue(T[] array) throws OperationArrayException;

    public Double calculateSumValue(T[] array) throws OperationArrayException;

    public int calculatePositiveElementsCount(T[] array) throws OperationArrayException;

    public int calculateNegativeElementsCount(T[] array) throws OperationArrayException;


}
