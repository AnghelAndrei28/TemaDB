package com.dbma.thymeleaf.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Getter
@Entity
@Setter
public class Account {
    @Id
    private int accountId;
    @Column(name = "id")
    private int id;
    private String currency;
    private int deposit;
    private String iban;

    public String ibanGenerator() {
        String numericString = "0123456789";
        StringBuilder sb = new StringBuilder(16);

        if ((new Random()).nextInt(10) % 2 == 1) {
            sb.append(1); // for internal
        } else {
            sb.append(2); //for external
        }
        for (int i = 0; i < 15; i++) {
            int index = (int) (numericString.length() * Math.random());
            sb.append(numericString.charAt(index));
        }

        return sb.toString();
    }

    public Account() {
        this.iban = ibanGenerator();
    }
}
