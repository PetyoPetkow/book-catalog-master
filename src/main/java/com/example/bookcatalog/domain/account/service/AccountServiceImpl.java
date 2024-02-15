package com.example.bookcatalog.domain.account.service;

import com.example.bookcatalog.domain.account.entity.Account;
import com.example.bookcatalog.domain.account.mapper.AccountMapper;
import com.example.bookcatalog.domain.account.model.AccountCreateDto;
import com.example.bookcatalog.domain.account.model.AccountLoginDto;
import com.example.bookcatalog.domain.account.model.AccountReturnDto;
import com.example.bookcatalog.domain.account.repository.AccountRepository;
import com.example.bookcatalog.infrastructure.exception.AlreadyExistsException;
import com.example.bookcatalog.infrastructure.exception.IncorrectPasswordException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    private static final String ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXISTS = "Account with this email already exists";
    private static final String INVALID_EMAIL = "Invalid email";

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountReturnDto register(AccountCreateDto accountCreateDto) {
        if (accountRepository.existsAccountByEmail(accountCreateDto.email())) {
            throw new AlreadyExistsException(ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXISTS);
        }
        Account account = accountMapper.createDtoToAccount(accountCreateDto);
        account.setPassword(passwordEncoder.encode(accountCreateDto.password()));
        return accountMapper.accountToReturnDto(accountRepository.save(account));
    }

    @Override
    public AccountReturnDto login(AccountLoginDto accountLoginDto) {
        if (!accountRepository.existsAccountByEmail(accountLoginDto.email())) {
            throw new AlreadyExistsException(INVALID_EMAIL);
        }

        Account account = accountRepository.findAccountByEmail(accountLoginDto.email());

        if (passwordEncoder.matches(accountLoginDto.password(), account.getPassword())) {
            return accountMapper.accountToReturnDto(account);
        } else {
            throw new IncorrectPasswordException();
        }
    }
}
