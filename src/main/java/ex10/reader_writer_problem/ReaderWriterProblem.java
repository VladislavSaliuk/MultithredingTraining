package ex10.reader_writer_problem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterProblem {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    private final List<String> cash = new LinkedList<>();

    public void setElement(String element) {
        writeLock.lock();
        try {
            cash.add(element);
            System.out.println("Element " + element + " by thread " + Thread.currentThread().getName() + " is added");
        } finally {
            writeLock.unlock();
        }
    }

    public String getElement(int index) {
        readLock.lock();
        try {
            String element = cash.get(index);
            System.out.println("Element " + element + " by thread " + Thread.currentThread().getName() + " is printed");
            return element;
        } finally {
            readLock.unlock();
        }
    }

}
