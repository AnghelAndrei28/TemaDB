package com.dbma.thymeleaf.account;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    List<Account> findAccountByAccountId(int id);

    List<Account> findAccountById(int id);
}
