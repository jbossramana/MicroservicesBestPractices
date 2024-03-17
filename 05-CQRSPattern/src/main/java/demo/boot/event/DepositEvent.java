package demo.boot.event;

import demo.boot.model.Event;

public class DepositEvent extends Event {
    private double amount;

    public DepositEvent(String accountId, double amount) {
        super(accountId);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public double applyToBalance(double currentBalance) {
        // Update the balance by adding the deposit amount
        return currentBalance + amount;
    }
}