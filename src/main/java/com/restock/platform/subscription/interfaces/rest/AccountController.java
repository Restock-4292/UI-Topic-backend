package com.restock.platform.subscription.interfaces.rest;

import com.restock.platform.subscription.domain.model.aggregates.Account;
import com.restock.platform.subscription.domain.model.commands.CreateAccountCommand;

import com.restock.platform.subscription.domain.services.AccountCommandService;
import com.restock.platform.subscription.domain.services.AccountQueryService;
import com.restock.platform.subscription.interfaces.rest.resources.RegisterAccountResource;
import com.restock.platform.subscription.interfaces.rest.resources.AccountResource;

import com.restock.platform.subscription.interfaces.rest.transform.RegisterAccountCommandFromResourceAssembler;
import com.restock.platform.subscription.interfaces.rest.transform.AccountResourceFromEntityAssembler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;

    public AccountController(AccountCommandService accountCommandService, AccountQueryService accountQueryService) {
        this.accountCommandService = accountCommandService;
        this.accountQueryService = accountQueryService;
    }


    @PostMapping
    public ResponseEntity<AccountResource> registerAccount(@RequestBody RegisterAccountResource resource) {
        CreateAccountCommand createAccountCommand = RegisterAccountCommandFromResourceAssembler.toCommandFromResource(resource);

        Optional<Account> createdAccount = accountCommandService.handle(createAccountCommand);
        assert createdAccount.orElse(null) != null;
        AccountResource accountResource = AccountResourceFromEntityAssembler.toResourceFromEntity(createdAccount.orElse(null));
        return new ResponseEntity<>(accountResource, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResource> getAccountById(@PathVariable UUID accountId) {
        Optional<Account> accountOptional = accountQueryService.findById(accountId);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Account account = accountOptional.get();
        AccountResource accountResource = AccountResourceFromEntityAssembler.toResourceFromEntity(account);
        return ResponseEntity.ok(accountResource);
    }
}