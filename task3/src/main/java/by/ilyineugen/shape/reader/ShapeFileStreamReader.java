package by.ilyineugen.shape.reader;

import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.validator.FileValidator;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ShapeFileStreamReader {

    ShapeFileStreamReader() {
    }

    public static Stream<String> getStream(String filePath) throws ShapeException, FileNotFoundException {
        Stream<String> streamOfShapeTxtData;
        if (FileValidator.isValid(filePath)) {
            FileInputStream fileInputStream;
            fileInputStream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            streamOfShapeTxtData = br.lines();
        } else {
            throw new ShapeException("hello 123");
        }
        return streamOfShapeTxtData;
    }
}
