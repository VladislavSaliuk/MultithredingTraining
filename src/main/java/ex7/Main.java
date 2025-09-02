package ex7;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleAtomicCounter counter = new SimpleAtomicCounter();

        Runnable runnable = () -> {
            long start = System.currentTimeMillis();
            long duration = 15_000;

            while (System.currentTimeMillis() - start < duration) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");
        Thread thread3 = new Thread(runnable, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Counter: " + counter.getCounter());

        counter.shutdown();
    }

}
