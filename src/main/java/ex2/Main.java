package ex2;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        Runnable task1 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    bank.topUpBalance(100L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    bank.topUpBalance(200L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(task1);
        t1.setName("Thread-1");
        Thread t2 = new Thread(task2);
        t2.setName("Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Current balance: " + bank.getCurrentBalance());


    }
}
