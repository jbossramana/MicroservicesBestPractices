package demo.hexagon.adapter.persistence.jpa.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("demo.hexagon.adapter.persistence.jpa")
@ConditionalOnProperty(
        value="adapter.driven.persistence.type",
        havingValue= "spring-data-jpa")
public class JpaAdapterConfiguration {
    private static final Logger LOGGER = LogManager.getLogger(JpaAdapterConfiguration.class);

    @PostConstruct
    public void init() {
        LOGGER.info("Driven persistence adapter was initialized with: spring-data-jpa!");
    }
}
