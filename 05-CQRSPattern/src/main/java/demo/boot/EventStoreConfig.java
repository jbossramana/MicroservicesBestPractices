package demo.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.boot.event.EventStore;

@Configuration
public class EventStoreConfig {
    @Bean
    public EventStore eventStore() {
        return new EventStore();
    }
}
