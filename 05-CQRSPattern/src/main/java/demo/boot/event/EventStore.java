package demo.boot.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.boot.model.Event;

public class EventStore {
    private Map<String, List<Event>> events = new HashMap<>();

    public void addEvent(String accountId, Event event) {
        events.computeIfAbsent(accountId, k -> new ArrayList<>()).add(event);
    }

    public List<Event> getEventsForAccount(String accountId) {
        return events.getOrDefault(accountId, Collections.emptyList());
    }
}
