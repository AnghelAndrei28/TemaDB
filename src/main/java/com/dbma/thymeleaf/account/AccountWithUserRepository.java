package com.dbma.thymeleaf.account;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountWithUserRepository extends CrudRepository<AccountWithUser, Integer> {
    List<AccountWithUser> findAccountWithUserByAccountId(int id);
}
