package by.ilyineugen.shape.validator;

import java.io.File;
import java.nio.file.Files;

public class FileValidator {

    public static boolean isValid(File file) {
        return file.exists() && file.length() > 0;
    }

    public static boolean isValid(String filePath) {
        File file = new File(filePath);
        return isValid(file);
    }
}
