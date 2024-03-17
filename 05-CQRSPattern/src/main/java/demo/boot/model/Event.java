package demo.boot.model;

import java.util.Date;

public abstract class Event {
    private String accountId;
    private Date timestamp;

    public Event(String accountId) {
        this.accountId = accountId;
        this.timestamp = new Date();
    }

    public String getAccountId() {
        return accountId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // Subclasses should implement applyToBalance to update the account balance.
    public abstract double applyToBalance(double currentBalance);
}
