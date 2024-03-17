package demo.boot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandService {
    private final EventStore eventStore;

    @Autowired
    public AccountCommandService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void createAccount(String accountId) {
        // Create Account Event
        AccountCreatedEvent event = new AccountCreatedEvent(accountId);
        eventStore.addEvent(accountId, event);
    }

    public void deposit(String accountId, double amount) {
        // Deposit Event
        DepositEvent event = new DepositEvent(accountId, amount);
        eventStore.addEvent(accountId, event);
    }
}
