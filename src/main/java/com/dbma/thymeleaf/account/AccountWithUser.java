package com.dbma.thymeleaf.account;

import com.dbma.thymeleaf.user.Users;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class AccountWithUser {
    @Id
    private int accountId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Users user;
    private String currency;
    private int deposit;
    private String iban;

    public AccountWithUser(Users user, Account account) {
        this.accountId = account.getAccountId();
        this.currency = account.getCurrency();
        this.deposit = account.getDeposit();
        this.user = user;
        this.iban = account.getIban();
    }

    public AccountWithUser() {

    }
}
