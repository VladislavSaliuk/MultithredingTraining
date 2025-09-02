package ex6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String msg = "Message " + i;
                    queue.put(msg);
                    System.out.println("Produced: " + msg);
                    Thread.sleep(200);
                }
                queue.put("END");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                while (true) {
                    String msg = queue.take();
                    if ("END".equals(msg)) break;
                    System.out.println("Consumed: " + msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(producer);
        executor.submit(consumer);

        executor.shutdown();
    }
}
