package work03;

import work01.Utilitor;
import work02.Person;
public class Account {
    private static long nextNo = 1000_000_000;
    private final long no;
    private Person owner;
    private double balance;

    public Account(Person owner) {
        if (owner == null) {
            throw new NullPointerException();
        }
        this.owner = owner;
        this.balance = 0.0;

        long result = Utilitor.computeIsbn10(nextNo);
        while (result == 10) {
            nextNo++;
            result = Utilitor.computeIsbn10(nextNo);
        }
        this.no = 10 * nextNo + result;
        nextNo++;
    }

    public long getNo() {
        return no;
    }

    public Person getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double amount) {
        return this.balance += Utilitor.testPositive(amount);
    }

    public double withdraw(double amount) {
        if (amount > this.balance) {
            throw new IllegalAccessException();
        }
        return this.balance -= Utilitor.testPositive(amount);
    }

    public void transfer(double amount, Account account) {
        if(account == null || balance < Utilitor.testPositive(amount)) {
            throw new IllegalArgumentException();
        }
        this.withdraw(amount);
        account.deposit(amount);
    }
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account(").append(no).append(",").append(balance).append(")");
        return sb.toString();
    }
}
