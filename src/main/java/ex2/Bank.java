package ex2;

public class Bank {

    private Long balance;

    public Bank() {
        this.balance = 0L;
    }

    public synchronized void topUpBalance(Long total) {
        this.balance += total;
    }

    public synchronized void withdrawal(Long total) {
        this.balance -= total;
    }

    public synchronized Long getCurrentBalance() {
        return balance;
    }
}
