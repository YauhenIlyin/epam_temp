package by.ilyineugene.arrayapp.service.impl;

import by.ilyineugene.arrayapp.service.ArrayElementSorter;

public class ArrayElementSorterImpl<T extends Number> implements ArrayElementSorter {


    @Override
    public void bubbleSort(Number[] array) {
        boolean isSorted = false;
        int lastUnsortedIndex = array.length - 1;
        while (!isSorted) {
            isSorted = true;
            for (int index = 0; index < lastUnsortedIndex; index++) {
                if (array[index].doubleValue() > array[index + 1].doubleValue()) {
                    Number container = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = container;
                    isSorted = false;
                }
            }
            lastUnsortedIndex--;
        }
    }

    @Override
    public void shakerSort(Number[] array) {
        int leftBorderIndex = 0;
        int rightBorderIndex = array.length - 1;
        boolean isSorted = false;
        while (leftBorderIndex <= rightBorderIndex && !isSorted) {
            isSorted = true;
            for (int index = leftBorderIndex; index < rightBorderIndex; index++) {
                if (array[index].doubleValue() > array[index + 1].doubleValue()) {
                    Number container = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = container;
                    isSorted = false;
                }
            }
            rightBorderIndex--;
            for (int index = rightBorderIndex; index > leftBorderIndex; index--) {
                if (array[index].doubleValue() < array[index - 1].doubleValue()) {
                    Number container = array[index];
                    array[index] = array[index - 1];
                    array[index - 1] = container;
                    isSorted = false;
                }
            }
            leftBorderIndex++;
        }
    }

    @Override
    public void selectSort(Number[] array) {
        for (int currentIndex = 0; currentIndex < array.length - 1; currentIndex++) {
            int minIndex = currentIndex;
            for (int index = currentIndex + 1; index < array.length; index++) {
                if (array[minIndex].doubleValue() > array[index].doubleValue()) {
                    minIndex = index;
                }
            }
            if (currentIndex != minIndex) {
                Number container = array[currentIndex];
                array[currentIndex] = array[minIndex];
                array[minIndex] = container;
            }
        }
    }

}
