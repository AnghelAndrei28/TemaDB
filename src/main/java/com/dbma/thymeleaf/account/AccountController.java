package com.dbma.thymeleaf.account;

import com.dbma.thymeleaf.account.transferServices.TransferMoneyServices;
import com.dbma.thymeleaf.account.transferServices.TransferMoneyServicesExternal;
import com.dbma.thymeleaf.user.Users;
import com.dbma.thymeleaf.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TransferMoneyServices transferMoneyServices;

    @Autowired
    TransferMoneyServicesExternal transferMoneyServicesExternal;

    public AccountController(@Autowired @Qualifier("transferMoneyServicesInternal") TransferMoneyServices transferMoneyServices) {
        this.transferMoneyServices = transferMoneyServices;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byUserId/{userId}")
    public List<AccountWithUser> getAccountByUserId(@PathVariable("userId") int userId) {
        List<Account> accounts = new ArrayList<>();
        List<AccountWithUser> accountWithUsers = new ArrayList<>();
        for (Account o : accountRepository.findAccountById(userId)) {
            List<Account> full = accountRepository.findAccountByAccountId(o.getAccountId());
            List<Users> users = usersRepository.findUsersById(o.getId());
            accountWithUsers.add(new AccountWithUser(users.get(0), full.get(0)));
        }
        return accountWithUsers;
    }

    //Cod primit de la Anemona
    @PostMapping("/sendMoney/{sum}")
    public String transferMoney(@RequestBody List<String> ibans, @PathVariable int sum) {
        if (ibans.size() != 2) {
            return "Please try again with only 2 ibans";
        } else {
            String fstIban = ibans.get(0);
            String sndIban = ibans.get(1);

            List<Account> accounts = getAccountByIban(fstIban);
            if (!accounts.isEmpty()) {
                Account source = accounts.get(0);
                System.out.println("There is an account with the first iban!");

                if (source.getDeposit() > sum) {
                    System.out.println("There are enough funds for this transfer!");
                    transferMoneyServices.sendMoneyTo(sndIban, sum, fstIban);
                    return transferMoneyServicesExternal.sendMoneyTo(sndIban, sum, fstIban);
                } else {
                    return "Not enough money in the source account";
                }
            } else {
                return "No account with such iban exists -> " + fstIban;
            }
        }
    }

    public List<Account> getAccountByIban(String iban) {
        return accountRepository.findAccountByIban(iban);
    }

}
