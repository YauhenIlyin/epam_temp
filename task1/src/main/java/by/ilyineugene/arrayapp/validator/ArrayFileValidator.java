package by.ilyineugene.arrayapp.validator;

import java.io.File;

public class ArrayFileValidator {

    private ArrayFileValidator() {
    }

    public static boolean isValid(File file) {
        return file.exists() && file.length() > 0;
    }

}
