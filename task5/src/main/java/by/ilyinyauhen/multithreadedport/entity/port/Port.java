package by.ilyinyauhen.multithreadedport.entity.port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class Port {

    private static AtomicBoolean isInitialize = new AtomicBoolean(false);
    private static Port instance;

    private List<Pier> listOfFreePiers = new ArrayList<Pier>();

    private Port() {
        for (int i = 0; i < 6; i++) {
            listOfFreePiers.add(Pier.getInstance());
        }
    }

    public static Port getInstance() {
        while (instance == null) {
            if (isInitialize.compareAndSet(false, true)) {
                instance = new Port();
            }
        }
        return instance;
    }


}
