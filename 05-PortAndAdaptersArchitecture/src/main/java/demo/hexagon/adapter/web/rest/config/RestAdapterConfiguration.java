package demo.hexagon.adapter.web.rest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("demo.hexagon.adapter.web.rest")
@ConditionalOnProperty(
        value="adapter.driver.web.type",
        havingValue= "rest-api",
        matchIfMissing = true)
public class RestAdapterConfiguration {
    private static final Logger LOGGER = LogManager.getLogger(RestAdapterConfiguration.class);

    @PostConstruct
    public void init() {
        LOGGER.info("Driver web adapter was initialized with rest api");
    }
}
