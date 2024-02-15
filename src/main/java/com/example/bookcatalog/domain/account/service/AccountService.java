package com.example.bookcatalog.domain.account.service;

import com.example.bookcatalog.domain.account.model.AccountCreateDto;
import com.example.bookcatalog.domain.account.model.AccountLoginDto;
import com.example.bookcatalog.domain.account.model.AccountReturnDto;

public interface AccountService {
    AccountReturnDto register(AccountCreateDto accountCreateDto);

    AccountReturnDto login(AccountLoginDto accountLoginDto);
}
