package ex3;


import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        BlockingQueue<Integer> queue = new BlockingQueue<>(1);

        Runnable put = new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Callable<Integer> take = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return queue.take();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<Integer> queueValue = executor.submit(take);
        executor.submit(put);
        System.out.println("Queue value: " + queueValue.get());

        executor.shutdown();

    }
}
