package ex8;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 10; i++) {
            int clientId = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    server.handleRequest(clientId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
