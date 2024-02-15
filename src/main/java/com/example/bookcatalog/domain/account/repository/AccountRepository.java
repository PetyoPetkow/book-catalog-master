package com.example.bookcatalog.domain.account.repository;

import com.example.bookcatalog.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByEmail(String email);

    Account findAccountByEmail(String email);
}
