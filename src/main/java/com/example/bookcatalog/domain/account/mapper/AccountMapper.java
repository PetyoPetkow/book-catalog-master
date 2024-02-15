package com.example.bookcatalog.domain.account.mapper;

import com.example.bookcatalog.domain.account.entity.Account;
import com.example.bookcatalog.domain.account.model.AccountCreateDto;
import com.example.bookcatalog.domain.account.model.AccountReturnDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account createDtoToAccount(AccountCreateDto accountCreateDto);
    AccountReturnDto accountToReturnDto(Account account);
}
