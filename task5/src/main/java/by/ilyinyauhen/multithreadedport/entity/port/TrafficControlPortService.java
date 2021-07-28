package by.ilyinyauhen.multithreadedport.entity.port;

import java.util.concurrent.atomic.AtomicBoolean;

public class TrafficControlPortService {

    private TrafficControlPortService instance;
    private AtomicBoolean isInitialized = new AtomicBoolean(false);
    private Port port;

    private TrafficControlPortService() {
        port = Port.
    }

    public TrafficControlPortService getInstance() {
        while (instance == false) {
            if (isInitialized.compareAndSet(false, true)) {
                instance = new TrafficControlPortService();
            }
        }
        return instance;
    }


    public Pier takeDock() {

    }

    public void freeDock(Pier pier) {

    }
}
