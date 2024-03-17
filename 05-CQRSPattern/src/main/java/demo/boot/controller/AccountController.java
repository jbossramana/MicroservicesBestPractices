package demo.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.event.AccountCommandService;
import demo.boot.event.AccountQueryService;

@RestController
public class AccountController {
    private final AccountCommandService commandService;
    private final AccountQueryService queryService;

    @Autowired
    public AccountController(
        AccountCommandService commandService,
        AccountQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/create")
    public void createAccount(@RequestParam String accountId) {
        commandService.createAccount(accountId);
    }

    @PostMapping("/deposit")
    public void deposit(
        @RequestParam String accountId,
        @RequestParam double amount
    ) {
        commandService.deposit(accountId, amount);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String accountId) {
        return queryService.getBalance(accountId);
    }
}
