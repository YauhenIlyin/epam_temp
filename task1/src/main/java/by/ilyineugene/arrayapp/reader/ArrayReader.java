package by.ilyineugene.arrayapp.reader;

import by.ilyineugene.arrayapp.exception.OperationArrayException;
import by.ilyineugene.arrayapp.validator.ArrayFileValidator;
import by.ilyineugene.arrayapp.validator.ArrayStringValidatorImpl;

import java.io.*;

public class ArrayReader {

    public ArrayReader() {

    }

    public String fileArrayReader(String filePath) throws OperationArrayException {
        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        BufferedReader br = null;
        boolean isFoundValidString = false;
        String string = "";
        if (ArrayFileValidator.isValid(file)) {
            try {
                fileInputStream = new FileInputStream(filePath);
                br = new BufferedReader(new InputStreamReader(fileInputStream));
                do {
                    string = br.readLine();
                    isFoundValidString = ArrayStringValidatorImpl.isValid(string);
                } while (!isFoundValidString);
            } catch (FileNotFoundException e) {
                throw new OperationArrayException("File not found...");
            } catch (IOException e) {
                throw new OperationArrayException("input output exception in buffered reader...");
            } finally {
                try {
                    br.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new OperationArrayException("buffered reader or InputStream .close() error ...");
                }
            }
        }
        if (isFoundValidString) {
            return string;
        } else {
            throw new OperationArrayException("there are no valid lines in the file...");
        }
    }
}
