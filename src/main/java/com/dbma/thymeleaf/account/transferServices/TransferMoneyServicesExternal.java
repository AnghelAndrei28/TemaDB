package com.dbma.thymeleaf.account.transferServices;



import com.dbma.thymeleaf.account.Account;
import com.dbma.thymeleaf.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyServicesExternal implements TransferMoneyServices {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String sendMoneyTo(String fstIban, int sum, String source) {
        System.out.println("Money sent external");
        Account sourceAccount = accountRepository.findAccountByIban(source).get(0);
        accountRepository.deleteById(sourceAccount.getAccountId());
        sourceAccount.setDeposit(sourceAccount.getDeposit() - sum);
        accountRepository.save(sourceAccount);

        return "Opperation succeded! Check account to see new balance.";
    }
}
