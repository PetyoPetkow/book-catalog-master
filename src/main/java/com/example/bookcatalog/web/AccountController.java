package com.example.bookcatalog.web;

import com.example.bookcatalog.domain.account.model.AccountCreateDto;
import com.example.bookcatalog.domain.account.model.AccountLoginDto;
import com.example.bookcatalog.domain.account.model.AccountReturnDto;
import com.example.bookcatalog.domain.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccountReturnDto> login(@Valid @RequestBody AccountLoginDto accountLoginDto) {
        return ResponseEntity.ok(accountService.login(accountLoginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountReturnDto> register(@Valid @RequestBody AccountCreateDto accountCreateDto) {
        return ResponseEntity.ok(accountService.register(accountCreateDto));
    }
}
