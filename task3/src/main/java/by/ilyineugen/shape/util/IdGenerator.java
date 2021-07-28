package by.ilyineugen.shape.util;

public class IdGenerator {
    private static long id = 0;

    public static long generateAndGetId() {
        return ++id;
    }
}
