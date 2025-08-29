package ex4;

public class Main {

    private volatile static boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        SimpleCounter counter = new SimpleCounter();

        Runnable runnable1 = () -> {
            while (running) {
                counter.increment();
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            running = false;
        };

        Thread thread1 = new Thread(runnable1, "Thread-1");
        Thread thread2 = new Thread(runnable2, "Thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Counter: " + counter.getCounter());
    }
}
