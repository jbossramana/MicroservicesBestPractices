package demo.boot.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.boot.model.Event;

@Service
public class AccountQueryService {
    private final EventStore eventStore;

    @Autowired
    public AccountQueryService(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public double getBalance(String accountId) {
        List<Event> events = eventStore.getEventsForAccount(accountId);
        // Calculate balance by applying events
        return events.stream()
            .mapToDouble(event -> event.applyToBalance(0))
            .sum();
    }
}
