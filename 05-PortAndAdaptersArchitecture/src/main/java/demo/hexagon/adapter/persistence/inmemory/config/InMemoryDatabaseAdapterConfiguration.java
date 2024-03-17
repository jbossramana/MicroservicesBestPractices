package demo.hexagon.adapter.persistence.inmemory.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("demo.hexagon.adapter.persistence.inmemory")
@ConditionalOnProperty(
        value = "adapter.driven.persistence.type",
        havingValue = "in-memory",
        matchIfMissing = true)
public class InMemoryDatabaseAdapterConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(InMemoryDatabaseAdapterConfiguration.class);

    @PostConstruct
    public void init() {
        LOGGER.info("Driven persistence adapter was initialized with: in-memory database!");
    }

}
