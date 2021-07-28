package by.ilyineugen.shape.main;

import by.ilyineugen.shape.entity.EntityQuadrangle;
import by.ilyineugen.shape.entity.PlanimetricPoint;
import by.ilyineugen.shape.entity.PlanimetricShape;
import by.ilyineugen.shape.exception.ShapeException;
import by.ilyineugen.shape.parser.QuadrangleParser;
import by.ilyineugen.shape.reader.ShapeFileStreamReader;
import by.ilyineugen.shape.repository.impl.QuadrangleSquareSpecification;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ShapeException, FileNotFoundException {
        Stream<String> stream = ShapeFileStreamReader.getStream("src/main/resources/data/data.txt");
        QuadrangleParser parser = new QuadrangleParser();
        List<double[]> list = parser.getListOfShapes(stream);
        System.out.println(list.get(0));
        System.out.println("1");
        System.out.println(list.size());
        for (double[] arr : list) {
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        PlanimetricShape shape = new EntityQuadrangle(new PlanimetricPoint(0.,0.),new PlanimetricPoint(0.,1.),new PlanimetricPoint(1.,1.),new PlanimetricPoint(1.,0.));
        QuadrangleSquareSpecification spec = new QuadrangleSquareSpecification(shape);
    }
}
