package demo.hexagon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import demo.hexagon.adapter.persistence.inmemory.config.InMemoryDatabaseAdapterConfiguration;
import demo.hexagon.adapter.persistence.jpa.config.JpaAdapterConfiguration;
import demo.hexagon.adapter.web.rest.config.RestAdapterConfiguration;

@SpringBootApplication
@Import({JpaAdapterConfiguration.class, InMemoryDatabaseAdapterConfiguration.class, RestAdapterConfiguration.class})
@ComponentScan(basePackages = "demo.hexagon",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "demo.hexagon.adapter.*"))
public class PortAndAdaptersApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortAndAdaptersApplication.class, args);
    }

}
