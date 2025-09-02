package ex8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Race {
    public static void main(String[] args) {
        int runners = 5;

        CyclicBarrier barrier = new CyclicBarrier(runners,
                () -> System.out.println("\n>>> All runners are ready! Race starts! <<<\n"));

        for (int i = 1; i <= runners; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Runner " + id + " is approaching the start line...");
                    Thread.sleep(1000 * id);
                    System.out.println("Runner " + id + " is ready!");
                    barrier.await();
                    System.out.println("Runner " + id + " started running!");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
