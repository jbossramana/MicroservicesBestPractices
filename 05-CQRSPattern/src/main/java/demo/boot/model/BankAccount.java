package demo.boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double balance;

    @Version
    private Long version;

    public BankAccount() {
        // Default constructor for JPA
    }

    public BankAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        // Handle the deposit event and update balance
        this.balance += amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
}
