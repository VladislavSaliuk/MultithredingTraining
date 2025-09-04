package ex10.dining_philosophers_problem;

public class Philosopher extends Thread {

    private final Object left;

    private final Object right;

    public Philosopher(Object left, Object right, int number) {
        this.left = left;
        this.right = right;
        this.setName("Philosopher " + number);
    }

    @Override
    public void run() {
        try {
            while (true) {
                act(" is thinking");
                synchronized (left) {
                    act(" picked up the left chopstick");
                    synchronized (right) {
                        act(" picked up the right chopstick and is eating");
                    }
                    act(" put down both chopsticks, stopped eating and thinking");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void act(String message) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": " + message);
        Thread.sleep((int)(Math.random() * 100));
    }

}
