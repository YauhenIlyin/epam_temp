package by.ilyineugen.handling.validator;

import java.io.File;

public class TextValidator {

    private TextValidator() {
    }

    public static boolean validateTextFile(File file) {
        return file.exists() && file.length() > 0L;
    }

}
