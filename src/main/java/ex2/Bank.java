package ex2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private Long balance;

    private static final Lock lock = new ReentrantLock();

    public Bank() {
        this.balance = 0L;
    }

    public void topUpBalance(Long total) throws InterruptedException {
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            Thread.sleep(5000);
            try {
                this.balance += total;
                System.out.println(Thread.currentThread().getName() + " increased balance: " + this.balance);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " : transaction is cancelled!");
        }
    }

    public void withdrawal(Long total) throws InterruptedException {
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                this.balance -= total;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Transaction is cancelled!");
        }
    }

    public Long getCurrentBalance() throws InterruptedException {
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                return this.balance;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Transaction is cancelled!");
            return null;
        }
    }

}
