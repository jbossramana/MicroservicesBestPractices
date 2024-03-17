package demo.boot.event;

import demo.boot.model.Event;

public class AccountCreatedEvent extends Event {
    public AccountCreatedEvent(String accountId) {
        super(accountId);
    }

    public double applyToBalance(double currentBalance) {
        // Account creation event does not affect the balance
        return currentBalance;
    }
}