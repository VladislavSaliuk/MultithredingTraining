package ex1.runnable;

public class Main {
    public static void main(String[] args) {
        Runnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable);
        thread1.setName("MyThread-1");
        Thread thread2 = new Thread(myRunnable);
        thread2.setName("MyThread-2");
        Thread thread3 = new Thread(myRunnable);
        thread3.setName("MyThread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
