package ex2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();

        Runnable runnableTask = () -> {
            for(int i = 0; i < 10; i++) {
                bank.topUpBalance(30L);
            }
        };

        Thread thread1 = new Thread(runnableTask);
        Thread thread2 = new Thread(runnableTask);
        Thread thread3 = new Thread(runnableTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Current balance: " + bank.getCurrentBalance());


    }
}
