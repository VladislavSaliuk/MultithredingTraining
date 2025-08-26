package ex1.callable;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> callableTask = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return Thread.currentThread().getName() + " finished!";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> result1 = executorService.submit(callableTask);
        Future<String> result2 = executorService.submit(callableTask);
        Future<String> result3 = executorService.submit(callableTask);

        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println(result3.get());

        executorService.shutdown();

    }

}
