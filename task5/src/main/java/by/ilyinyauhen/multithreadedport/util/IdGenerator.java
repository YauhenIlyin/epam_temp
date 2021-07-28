package by.ilyinyauhen.multithreadedport.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static AtomicInteger pierId = new AtomicInteger(0);
    private static AtomicInteger shipId = new AtomicInteger(0);

    public static long getPierId() {
        return pierId.incrementAndGet();
    }

    public static long getShipId() {
        return shipId.incrementAndGet();
    }


}
