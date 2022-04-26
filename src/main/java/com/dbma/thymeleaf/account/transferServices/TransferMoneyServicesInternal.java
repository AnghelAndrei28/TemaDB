package com.dbma.thymeleaf.account.transferServices;

import com.dbma.thymeleaf.account.Account;
import com.dbma.thymeleaf.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class TransferMoneyServicesInternal implements TransferMoneyServices {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String sendMoneyTo(String iban, int sum, String source) {
        List<Account> accounts = accountRepository.findAccountByIban(iban);
        if (!accounts.isEmpty()) {
            System.out.println("There is an account with the destination iban!");
            Account destination = accounts.get(0);
            accountRepository.deleteById(destination.getAccountId());
            destination.setDeposit(destination.getDeposit() + sum);
            accountRepository.save(destination);
            System.out.println("I have updated the deposit of destination account!");

            return "Opperation succeded! Check account to see new balance.";
        } else {
            return "No account with such iban exists from interal -> " + iban;
        }
    }
}
