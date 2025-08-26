package ex1.thread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        thread1.setName("MyThread-1");
        Thread thread2 = new MyThread();
        thread2.setName("MyThread-2");
        Thread thread3 = new MyThread();
        thread3.setName("MyThread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
