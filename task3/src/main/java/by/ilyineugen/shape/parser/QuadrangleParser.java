package by.ilyineugen.shape.parser;

import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.validator.RegularQuadrangleValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuadrangleParser {
    public QuadrangleParser() {
    }

    public List<double[]> getListOfShapes(Stream<String> streamOfShapeData) throws ShapeException {
        List<double[]> listOfCoordinateArrays = new ArrayList<>();
        List<String> listOfStringShapes = new ArrayList<String>();

        listOfStringShapes = streamOfShapeData.collect(Collectors.toList());

        streamOfShapeData.close();
        for (String line : listOfStringShapes) {
            double[] array = Arrays.stream(line.split(" ")).mapToDouble((String str) -> Double.parseDouble(str)).toArray();
            if (RegularQuadrangleValidator.isValid(array)) {
                listOfCoordinateArrays.add(array);
            }
        }
        if (listOfCoordinateArrays.size() == 0) {
            throw new ShapeException("2222");
        }
        return listOfCoordinateArrays;
    }

}
