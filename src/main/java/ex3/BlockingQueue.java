package ex3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();

    private final Integer capacity;

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    public BlockingQueue(Integer capacity) {
        this.capacity = capacity;
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " is waiting: queue is full");
                notFull.await();
            }
            queue.add(t);
            System.out.println(Thread.currentThread().getName() + " added item: " + t + " | Queue size: " + queue.size());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {

            while(queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " is waiting: queue is empty");
                notEmpty.await();
            }
            T item = queue.poll();
            System.out.println(Thread.currentThread().getName() + " took item: " + item + " | Queue size: " + queue.size());
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

}
