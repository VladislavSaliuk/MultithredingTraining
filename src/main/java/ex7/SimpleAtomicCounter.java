package ex7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleAtomicCounter {

    private final AtomicInteger counter;
    private final ScheduledExecutorService scheduler;

    public SimpleAtomicCounter() {
        this.counter = new AtomicInteger(0);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        this.scheduler.scheduleAtFixedRate(
                this::reset,
                10,
                10,
                TimeUnit.SECONDS
        );
    }

    public void increment() {
        int newValue = this.counter.incrementAndGet();
        System.out.println("Counter incremented, new value: " + newValue);
    }

    public int getCounter() {
        int value = this.counter.get();
        System.out.println("Current counter value: " + value);
        return value;
    }

    public void reset() {
        int oldValue = this.counter.getAndSet(0);
        System.out.println("Counter reset from " + oldValue + " to 0");
    }

    public void shutdown() throws InterruptedException {
        scheduler.shutdown();

        if (!scheduler.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
            scheduler.shutdownNow();
        }
        System.out.println("Scheduler stopped");
    }

}
