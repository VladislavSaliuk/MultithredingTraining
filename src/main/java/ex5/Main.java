package ex5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            executorService.execute(createTask("Task-" + i));
        }


        executorService.shutdown();

        if (executorService.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
            executorService.shutdownNow();
        }

    }

    private static Runnable createTask(String name) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " -> " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }

}