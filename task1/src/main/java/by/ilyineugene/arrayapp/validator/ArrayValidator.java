package by.ilyineugene.arrayapp.validator;

public final class ArrayValidator {

    private ArrayValidator() {
    }

    public static boolean isValid(Number[] array) {
        return array != null && array.length > 0;
    }

}
