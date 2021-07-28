package by.ilyinyauhen.multithreadedport.entity.port;

import by.ilyinyauhen.multithreadedport.util.IdGenerator;

import java.util.concurrent.atomic.AtomicBoolean;

class Pier {
    private long id;
    private static Pier instance;
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private int containerSize = 10;

    private Pier() {
        this.id = IdGenerator.getPierId();
    }

    public static Pier getInstance() {
        while (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                instance = new Pier();
            }
        }
        return instance;
    }
}
