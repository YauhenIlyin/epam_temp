package by.ilyineugene.arrayapp.parser.impl;

import by.ilyineugene.arrayapp.parser.ArrayParser;

public class ArrayParserImpl implements ArrayParser {

    private char[] allowedSeparators = new char[]{' ', '-', ','}; //in properties file ?

    public Number[] stringToArrayNumberParser(String string) {
        int stringLength = string.length();
        int arrayLength = (int) (stringLength / 3 * 2 + 5);
        Number[] array = new Number[arrayLength];
        int lastFreeArrayIndex = 0;
        int numberBeginIndex = 0;
        int numberEndIndex = 0;
        char currentCharacter;
        Number number;
        int index;
        boolean isFoundNumber = false;
        for (index = 0; index < stringLength; index++) {
            boolean isSeparator = false;
            currentCharacter = string.charAt(index);
            for (int separatorIndex = 0; separatorIndex < allowedSeparators.length; separatorIndex++) {
                if (currentCharacter == allowedSeparators[separatorIndex]) {
                    isSeparator = true;
                }
            }
            if (!isSeparator && !isFoundNumber) {
                isFoundNumber = true; //if not a separator and the first digit of the number
                numberBeginIndex = index;
            } else if (isSeparator && isFoundNumber) {
                isFoundNumber = false; //if separator, after the start of the number
                numberEndIndex = index - 1;
                System.out.println("length " + string.length());
                System.out.println("begin: " + numberBeginIndex);
                System.out.println("end " + (numberEndIndex + 1));
                number = Double.parseDouble(string.substring(numberBeginIndex, numberEndIndex + 1));
                addNumberToArrayByIndex(array, number, lastFreeArrayIndex);
                lastFreeArrayIndex++;
            }
        }
        if (isFoundNumber) {
            numberEndIndex = index - 1;
            number = Double.parseDouble(string.substring(numberBeginIndex, numberEndIndex + 1));
            addNumberToArrayByIndex(array, number, lastFreeArrayIndex); //case, where the number is the last element of the string
            lastFreeArrayIndex++;
        }
        return arrayFinalFormation(array, lastFreeArrayIndex);
    }

    //check if there is free space, and add number to array
    private void addNumberToArrayByIndex(Number[] array, Number number, int index) {
        if (index >= array.length) {
            array = arrayExtension(array);
        }
        array[index] = number;
    }

    //expandable, if there is not enough space for new elements
    private Number[] arrayExtension(Number[] array) {
        int extendedLength = (int) (array.length * 1.3 + 2);
        Number[] extendedArray = new Number[extendedLength];
        for (int index = 0; index < array.length; index++) {
            extendedArray[index] = array[index];
        }
        return extendedArray;
    }

    //array without free slots
    private Number[] arrayFinalFormation(Number[] array, int countOfElements) {
        Number[] completedArrayVersion = new Number[countOfElements];
        for (int index = 0; index < completedArrayVersion.length; index++) {
            completedArrayVersion[index] = array[index];
        }
        return completedArrayVersion;
    }
}
