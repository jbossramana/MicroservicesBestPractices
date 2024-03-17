package demo.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.boot.event.AccountCommandService;
import jakarta.annotation.PostConstruct;

@Component
public class SampleDataInitializer {

    private final AccountCommandService commandService;

    @Autowired
    public SampleDataInitializer(AccountCommandService commandService) {
        this.commandService = commandService;
    }

    @PostConstruct
    public void initializeSampleData() {
        // Create some sample accounts and deposit initial amounts
        commandService.createAccount("account1");
        commandService.deposit("account1", 100.0);

        commandService.createAccount("account2");
        commandService.deposit("account2", 200.0);

        commandService.createAccount("account3");
        commandService.deposit("account3", 50.0);
    }
}
