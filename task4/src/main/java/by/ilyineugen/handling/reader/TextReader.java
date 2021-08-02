package by.ilyineugen.handling.reader;

import by.ilyineugen.handling.exception.CompositeAppException;
import by.ilyineugen.handling.validator.TextValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {

    public static String readTextFile(String filePath) throws CompositeAppException {
        String text = "";
        File file = new File(filePath);
        if (TextValidator.validateTextFile(file)) {
            Path path = Paths.get(filePath);
            try {
                text = Files.readString(path);
            } catch (IOException e) {
                throw new CompositeAppException("TextReader.class : readTextFile(String filePath) : Files.readString(path) error");
            }
        }
        return text;
    }

}
