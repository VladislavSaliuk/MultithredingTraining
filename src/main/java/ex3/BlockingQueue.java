package ex3;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();

    private final Integer capacity;
    ;
    public BlockingQueue(Integer capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T t) throws InterruptedException {
        while(queue.size() == capacity) {
            System.out.println(Thread.currentThread().getName() + " is waiting: queue is full");
            wait();
        }
        queue.add(t);
        System.out.println(Thread.currentThread().getName() + " added item: " + t + " | Queue size: " + queue.size());
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while(queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " is waiting: queue is empty");
            wait();
        }
        T item = queue.poll();
        System.out.println(Thread.currentThread().getName() + " took item: " + item + " | Queue size: " + queue.size());
        notifyAll();
        return item;
    }

}
